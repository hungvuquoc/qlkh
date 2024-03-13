package com.example.qlkh.service.impl.warehouse;

import com.example.qlkh.constant.EbsSystem;
import com.example.qlkh.dto.request.searchs.ProductFloorSearchDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.dto.request.warehouse.inventories.WhInventoryItemReqDto;
import com.example.qlkh.dto.request.warehouse.inventories.WhInventoryReqDto;
import com.example.qlkh.dto.response.warehouse.inventories.WhInventoryRespDto;
import com.example.qlkh.dto.share.WhInventoryShare;
import com.example.qlkh.entity.Employee;
import com.example.qlkh.entity.product.*;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.entity.warehouse.WarehouseFloor;
import com.example.qlkh.entity.warehouse.inventories.WhInventory;
import com.example.qlkh.entity.warehouse.inventories.WhInventoryEmployeeConnect;
import com.example.qlkh.entity.warehouse.inventories.WhInventoryItem;
import com.example.qlkh.error.status.warehouse.WhInventoryStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.repository.EmployeeRepository;
import com.example.qlkh.repository.products.*;
import com.example.qlkh.repository.warehouse.WarehouseFloorRepository;
import com.example.qlkh.repository.warehouse.WarehouseRepository;
import com.example.qlkh.repository.warehouse.inventories.WhInventoryItemRepository;
import com.example.qlkh.repository.warehouse.inventories.WhInventoryRepository;
import com.example.qlkh.service.mapper.warehouse.inventories.WhInventoryMapper;
import com.example.qlkh.service.warehouse.WhInventoryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WhInventoryServiceImpl implements WhInventoryService {
    private final WhInventoryRepository whInventoryRepository;
    private final WhInventoryItemRepository whInventoryItemRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductTypeRepository productTypeRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final ProductUnitConnectRepository unitConnectRepository;
    private final ProductDetailRepository detailRepository;
    private final WarehouseFloorRepository floorRepository;
    private final ProductFloorRepository productFloorRepository;
    private final WhInventoryMapper whInventoryMapper;

    @Override
    public WhInventoryRespDto getById(@NonNull Long id) {
        WhInventory entity = whInventoryRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(WhInventoryStatus.ID_NOT_FOUND));
        return whInventoryMapper.mapEntityToDto(entity);
    }

    @Override
    public Page<WhInventoryRespDto> search(@NonNull SearchDto dto) {
        List<WhInventory> inventories = whInventoryRepository.search(dto);
        List<WhInventoryRespDto> content = inventories.stream()
                .map(whInventoryMapper::mapEntityToHiddenDto).collect(Collectors.toList());
        long total = whInventoryRepository.count(dto);
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Override
    public WhInventoryRespDto create(@NonNull WhInventoryReqDto dto) {
        dto.setId(null);
        WhInventoryShare share = new WhInventoryShare();
        this.validateDto(dto, share);
        WhInventory entity = new WhInventory();
        this.mapDtoToEntity(dto, entity, share);

        entity = whInventoryRepository.save(entity);
        return whInventoryMapper.mapEntityToDto(entity);
    }

    @Override
    public WhInventoryRespDto update(@NonNull Long id, @NonNull WhInventoryReqDto dto) {
        dto.setId(id);
        WhInventory entity = whInventoryRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(WhInventoryStatus.ID_NOT_FOUND));
        WhInventoryShare share = new WhInventoryShare();
        this.validateDto(dto, share);
        whInventoryItemRepository.deleteByParentId(id);
        this.mapDtoToEntity(dto, entity, share);

        entity = whInventoryRepository.save(entity);
        return whInventoryMapper.mapEntityToDto(entity);
    }

    @Override
    public Boolean deleteById(@NonNull Long id) {
        return null;
    }

    private void mapDtoToEntity(@NonNull WhInventoryReqDto dto, @NonNull WhInventory entity, @NonNull WhInventoryShare share) {
        if (dto.getId() == null) {
            entity.setCode(this.generateCode());
        }

        Warehouse warehouse = share.getWarehouse();
        entity.setWarehouse(warehouse);
        entity.setWhCode(warehouse.getCode());
        entity.setWhName(warehouse.getName());
        entity.setProductType(share.getProductType());
        entity.setStatus(dto.getStatus());
        entity.setInputDate(dto.getInputDate());
        entity.setNote(dto.getNote());

        Map<Long, Employee> employeeMap = share.getEmployees();
        List<WhInventoryEmployeeConnect> employeeConnects = new ArrayList<>();
        for (Long key : employeeMap.keySet()) {
            WhInventoryEmployeeConnect employeeConnect = new WhInventoryEmployeeConnect();
            employeeConnect.setEmployee(employeeMap.get(key));
            employeeConnect.setInventory(entity);
        }
        entity.setEmployees(employeeConnects);

        List<WhInventoryItem> items = new ArrayList<>();
        for (WhInventoryItemReqDto itemDto : dto.getItems()) {
            items.add(this.createItemEntity(itemDto, share));
        }
        entity.setItems(items);
    }

    private WhInventoryItem createItemEntity(@NonNull WhInventoryItemReqDto dto, @NonNull WhInventoryShare share) {
        WhInventoryItem item = new WhInventoryItem();
        Product product = share.getProducts().get(dto.getProductId());
        item.setProduct(product);
        item.setUnitCheck(share.getUnits().get(dto.getUnitCheckId()));
        ProductDetail detail = share.getDetails().get(dto.getProductDetailId());
        item.setProductDetail(detail);
        item.setFloor(share.getFloors().get(dto.getFloorId()));
        item.setProductName(product.getName());
        item.setConsignmentNumber(dto.getConsignmentNumber());
        item.setQuantityReal(dto.getQuantityReal());
        item.setPrice(detail.getPrice());

        ProductFloorSearchDto searchDto = ProductFloorSearchDto.builder()
                .floorId(dto.getFloorId())
                .productId(dto.getProductId())
                .consignment(dto.getConsignmentNumber())
                .inputDate(dto.getInputDate())
                .unitTargetId(dto.getUnitCheckId())
                .productDetailId(dto.getProductDetailId())
                .build();
        ProductFloor productFloor = productFloorRepository.search(searchDto);

        item.setQuantityFake(productFloor.getQuantityTarget());
        return item;
    }

    private void validateDto(@NonNull WhInventoryReqDto dto, @NonNull WhInventoryShare share) {
        if (dto.getStatus() == null) {
            throw new DataRuntimeException(WhInventoryStatus.STATUS_IS_NULL);
        }

        if (dto.getInputDate() == null) {
            throw new DataRuntimeException(WhInventoryStatus.INPUT_DATE_IS_NULL);
        }

        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() -> new DataRuntimeException(WhInventoryStatus.WAREHOUSE_ID_NOT_FOUND));
        share.setWarehouse(warehouse);

        ProductType productType = productTypeRepository.findById(dto.getProductTypeId())
                .orElseThrow(() -> new DataRuntimeException(WhInventoryStatus.PRODUCT_TYPE_ID_NOT_FOUND));
        share.setProductType(productType);

        if (CollectionUtils.isEmpty(dto.getEmployeeIds())) {
            throw new DataRuntimeException(WhInventoryStatus.EMPLOYEES_IS_EMPTY);
        }
        List<Employee> employees = employeeRepository.getByIds(dto.getEmployeeIds());
        if (CollectionUtils.isEmpty(employees)) {
            throw new DataRuntimeException(WhInventoryStatus.EMPLOYEES_IS_EMPTY);
        }
        Map<Long, Employee> employeeMap = employees.stream().collect(Collectors.toMap(Employee::getId, Function.identity()));
        share.setEmployees(employeeMap);

        if (CollectionUtils.isEmpty(dto.getItems())) {
            throw new DataRuntimeException(WhInventoryStatus.ITEMS_IS_EMPTY);
        }

        for (WhInventoryItemReqDto itemDto : dto.getItems()) {
            this.validateItemDto(itemDto, share);
        }
    }

    private void validateItemDto(@NonNull WhInventoryItemReqDto dto, @NonNull WhInventoryShare share) {
        if (dto.getInputDate() == null) {
            throw new DataRuntimeException(WhInventoryStatus.ITEM_INPUT_DATE_IS_NULL);
        }

        if (dto.getQuantityReal() < 0d) {
            throw new DataRuntimeException(WhInventoryStatus.QUANTITY_REAL_IS_WRONG_FORMAT);
        }

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new DataRuntimeException(WhInventoryStatus.PRODUCT_ID_NOT_FOUND));
        share.getProducts().put(dto.getProductId(), product);

        ProductUnitConnect unitConnect = unitConnectRepository.findById(dto.getUnitCheckId())
                .orElseThrow(() -> new DataRuntimeException(WhInventoryStatus.UNIT_CONNECT_ID_NOT_FOUND));
        share.getUnits().put(dto.getUnitCheckId(), unitConnect);

        ProductDetail detail = detailRepository.findById(dto.getProductDetailId())
                .orElseThrow(() -> new DataRuntimeException(WhInventoryStatus.PRODUCT_DETAIL_ID_NOT_FOUND));
        share.getDetails().put(dto.getProductDetailId(), detail);

        WarehouseFloor floor = floorRepository.findById(dto.getFloorId())
                .orElseThrow(() -> new DataRuntimeException(WhInventoryStatus.FLOOR_ID_NOT_FOUND));
        share.getFloors().put(dto.getFloorId(), floor);
    }

    private String generateCode() {
        String maxCode = whInventoryRepository.getMaxCode();
        if (StringUtils.isEmpty(maxCode)) {
            maxCode = "0";
        }
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        return String.format(EbsSystem.WH_FORMAT_TRANSACTIONS_CODE, EbsSystem.WH_INVENTORY_PREFIX, currentYear, currentMonth, Integer.parseInt(maxCode) + 1);
    }
}
