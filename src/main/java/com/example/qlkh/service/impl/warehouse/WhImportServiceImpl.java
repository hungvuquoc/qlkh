package com.example.qlkh.service.impl.warehouse;

import com.example.qlkh.constant.AuthorityConstant;
import com.example.qlkh.constant.EbsSystem;
import com.example.qlkh.constant.Variables;
import com.example.qlkh.constant.enums.WhImportType;
import com.example.qlkh.constant.enums.WhTransactionStatus;
import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.request.searchs.ProductFloorSearchDto;
import com.example.qlkh.dto.request.searchs.WhImportSearchReqDto;
import com.example.qlkh.dto.request.warehouse.imports.WhImportItemDetailReqDto;
import com.example.qlkh.dto.request.warehouse.imports.WhImportItemReqDto;
import com.example.qlkh.dto.request.warehouse.imports.WhImportReqDto;
import com.example.qlkh.dto.response.warehouse.imports.WhImportRespDto;
import com.example.qlkh.dto.share.WhImportShare;
import com.example.qlkh.entity.Employee;
import com.example.qlkh.entity.product.*;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.entity.warehouse.WarehouseFloor;
import com.example.qlkh.entity.warehouse.imports.WhImport;
import com.example.qlkh.entity.warehouse.imports.WhImportItem;
import com.example.qlkh.entity.warehouse.imports.WhImportItemDetail;
import com.example.qlkh.error.status.warehouse.WhImportStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.repository.EmployeeRepository;
import com.example.qlkh.repository.products.*;
import com.example.qlkh.repository.warehouse.WarehouseFloorRepository;
import com.example.qlkh.repository.warehouse.WarehouseRepository;
import com.example.qlkh.repository.warehouse.imports.WhImportItemRepository;
import com.example.qlkh.repository.warehouse.imports.WhImportRepository;
import com.example.qlkh.service.mapper.warehouse.imports.WhImportMapper;
import com.example.qlkh.service.warehouse.WhImportService;
import com.example.qlkh.utils.EbsSecurityUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WhImportServiceImpl implements WhImportService {
    private final WhImportRepository whImportRepository;
    private final WhImportItemRepository whImportItemRepository;
    private final WarehouseRepository warehouseRepository;
    private final EmployeeRepository employeeRepository;
    private final SupplierRepository supplierRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductRepository productRepository;
    private final ProductUnitConnectRepository productUnitConnectRepository;
    private final ProductDetailRepository productDetailRepository;
    private final WarehouseFloorRepository warehouseFloorRepository;
    private final ProductFloorRepository productFloorRepository;
    private final WhImportMapper whImportMapper;

    @Override
    public WhImportRespDto getById(@NonNull Long id) {
        WhImport entity = whImportRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(WhImportStatus.ID_NOT_FOUND));
        return whImportMapper.mapEntityToDto(entity);
    }

    @Override
    public Page<WhImportRespDto> search(@NonNull WhImportSearchReqDto dto) {
        EbsPrincipal principal = EbsSecurityUtils.getCurrentUser();
        List<WhImport> whImports = whImportRepository.search(dto, principal);
        List<WhImportRespDto> content = whImports.stream()
                .map(e -> whImportMapper.mapEntityToDto(e, null))
                .collect(Collectors.toList());
        long total = whImportRepository.count(dto, principal);
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Override
    public WhImportRespDto create(@NonNull WhImportReqDto dto) {
        boolean hasPermission = EbsSecurityUtils.hasWhPermission(dto.getWarehouseId());
        if (!hasPermission) {
            hasPermission = EbsSecurityUtils.hasWhPermission(dto.getWarehouseId(),
                    AuthorityConstant.WH_IMPORT_CREATE);
        }

        if (!hasPermission) {
            throw new DataRuntimeException(WhImportStatus.ACCESS_DENIED_CREATE);
        }

        dto.setId(null);
        WhImportShare share = new WhImportShare();
        this.validateWhImportDtoAndSetDataShare(dto, share);

        WhImport entity = new WhImport();
        this.mapBasicDataToWhImport(dto, entity, share);
        entity = whImportRepository.save(entity);

        if (WhTransactionStatus.FINISH.equals(entity.getStatus())) {
            this.mapProductToFloor(entity);
        }

        return whImportMapper.mapEntityToDto(entity);
    }

    @Transactional(rollbackOn = {Exception.class})
    @Override
    public WhImportRespDto update(@NonNull Long id, @NonNull WhImportReqDto dto) {
        WhImport entity = whImportRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(WhImportStatus.ID_NOT_FOUND));

        if (WhTransactionStatus.FINISH.equals(entity.getStatus())) {
            throw new DataRuntimeException(WhImportStatus.HAS_FINISH);
        }

        boolean hasPermission = EbsSecurityUtils.hasWhPermission(dto.getWarehouseId());
        if (!hasPermission) {
            hasPermission = EbsSecurityUtils.hasWhPermission(dto.getWarehouseId(),
                    AuthorityConstant.WH_IMPORT_UPDATE_WAREHOUSE);
        }

        if (!hasPermission) {
            hasPermission = EbsSecurityUtils.hasWhPermission(dto.getWarehouseId(),
                    AuthorityConstant.WH_IMPORT_UPDATE_MY_CREATE) &&
                    EbsSecurityUtils.getUsername().equals(entity.getCreateBy());
        }

        if (!hasPermission) {
            throw new DataRuntimeException(WhImportStatus.ACCESS_DENIED_UPDATE);
        }

        dto.setId(id);
        WhImportShare share = new WhImportShare();
        this.validateWhImportDtoAndSetDataShare(dto, share);
        whImportItemRepository.deleteByParentId(id);
        this.mapBasicDataToWhImport(dto, entity, share);

        entity = whImportRepository.save(entity);

        if (WhTransactionStatus.FINISH.equals(entity.getStatus())) {
            this.mapProductToFloor(entity);
        }

        return whImportMapper.mapEntityToDto(entity);
    }

    @Override
    public Boolean deleteById(@NonNull Long id) {
        WhImport entity = whImportRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(WhImportStatus.ID_NOT_FOUND));

        if (WhTransactionStatus.FINISH.equals(entity.getStatus())) {
            throw new DataRuntimeException(WhImportStatus.HAS_FINISH);
        }

        whImportRepository.delete(entity);
        return true;
    }

    private void validateWhImportDtoAndSetDataShare(@NonNull WhImportReqDto dto, @NonNull WhImportShare share) {
        if (dto.getType() == null) {
            throw new DataRuntimeException(WhImportStatus.TYPE_IS_NULL);
        }

        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() -> new DataRuntimeException(WhImportStatus.WAREHOUSE_ID_NOT_FOUND));
        share.setWarehouse(warehouse);

        String username = EbsSecurityUtils.getUsername();

        Employee employee = employeeRepository.getByUsername(username);
        if (employee == null) {
            throw new DataRuntimeException(WhImportStatus.EMPLOYEE_ID_NOT_FOUND);
        }
        share.setEmployee(employee);

        if (WhImportType.BUY.equals(dto.getType())) {
            Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                    .orElseThrow(() -> new DataRuntimeException(WhImportStatus.SUPPLIER_ID_NOT_FOUND));
            share.setSupplier(supplier);
        }

        ProductType productType = productTypeRepository.findById(dto.getProductTypeId())
                .orElseThrow(() -> new DataRuntimeException(WhImportStatus.PRODUCT_TYPE_ID_NOT_FOUND));
        share.setProductType(productType);

        if (dto.getInputDate() == null) {
            throw new DataRuntimeException(WhImportStatus.INPUT_DATE_IS_NULL);
        }

        if (dto.getStatus() == null) {
            throw new DataRuntimeException(WhImportStatus.STATUS_IS_NULL);
        }

        if (CollectionUtils.isNotEmpty(dto.getItems())) {
            for (WhImportItemReqDto itemDto : dto.getItems()) {
                this.validateWhImportItemDtoAndSetDataShare(itemDto, share);
            }
        }
    }

    private void validateWhImportItemDtoAndSetDataShare(@NonNull WhImportItemReqDto dto, @NonNull WhImportShare share) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new DataRuntimeException(WhImportStatus.PRODUCT_ID_NOT_FOUND));
        share.getProducts().put(dto.getProductId(), product);

        ProductUnitConnect targetUnit = productUnitConnectRepository.findById(dto.getUnitTargetId())
                .orElseThrow(() -> new DataRuntimeException(WhImportStatus.UNIT_TARGET_ID_NOT_FOUND));
        share.getUnits().put(dto.getUnitTargetId(), targetUnit);

        ProductDetail productDetail = productDetailRepository.findById(dto.getProductDetailId())
                .orElseThrow(() -> new DataRuntimeException(WhImportStatus.PRODUCT_DETAIL_ID_NOT_FOUND));
        share.getProductDetails().put(dto.getProductDetailId(), productDetail);

        ProductUnitConnect sourceUnit = productUnitConnectRepository.getDefaultByProductId(dto.getProductId());
        if (sourceUnit == null) {
            throw new DataRuntimeException(WhImportStatus.PRODUCT_NOT_HAS_UNIT_SOURCE_NOT_EXIST);
        }
        share.getUnits().put(sourceUnit.getId(), sourceUnit);

        if (dto.getQuantityTarget() == null) {
            throw new DataRuntimeException(WhImportStatus.QUANTITY_TARGET_IS_NULL);
        }

        if (dto.getQuantityTarget() <= Variables.MIN_QUANTITY) {
            throw new DataRuntimeException(WhImportStatus.QUANTITY_TARGET_WRONG_FORMAT);
        }

        this.validateWhImportItemDetailsDtoAndSetDataShare(dto.getQuantityTarget(), dto.getDetails(), share);
    }

    private void validateWhImportItemDetailsDtoAndSetDataShare(@NonNull Double quantityTarget, List<WhImportItemDetailReqDto> dtos, @NonNull WhImportShare share) {
        if (CollectionUtils.isNotEmpty(dtos)) {
            double totalDetailQuantity = 0;
            for (WhImportItemDetailReqDto detailDto : dtos) {
                this.validateWhImportItemDetailDtoAndSetDataShare(detailDto, share);
                totalDetailQuantity += detailDto.getQuantity();
            }

            // validate tổng số ở itemDetail phải bằng với item
            if (Math.abs(quantityTarget - totalDetailQuantity) > Variables.DOUBLE_SAME) {
                throw new DataRuntimeException(WhImportStatus.QUANTITY_TARGET_NO_SAME_QUANTITY_DETAIL);
            }
        }
    }

    private void validateWhImportItemDetailDtoAndSetDataShare(@NonNull WhImportItemDetailReqDto dto, @NonNull WhImportShare share) {
        WarehouseFloor floor = warehouseFloorRepository.findById(dto.getFloorId())
                .orElseThrow(() -> new DataRuntimeException(WhImportStatus.FLOOR_ID_IS_NULL));
        share.getFloors().put(dto.getFloorId(), floor);

        if (dto.getQuantity() == null) {
            throw new DataRuntimeException(WhImportStatus.QUANTITY_DETAIL_IS_NULL);
        }

        if (dto.getQuantity() <= Variables.MIN_QUANTITY) {
            throw new DataRuntimeException(WhImportStatus.QUANTITY_DETAIL_WRONG_FORMAT);
        }
    }

    private void mapBasicDataToWhImport(@NonNull WhImportReqDto dto, @NonNull WhImport entity, @NonNull WhImportShare share) {
        entity.setType(dto.getType());
        if (dto.getId() == null) {
            entity.setCode(this.generateCode());
        }
        entity.setWarehouse(share.getWarehouse());
        entity.setEmployee(share.getEmployee());
        if (WhImportType.BUY.equals(dto.getType())) {
            entity.setSupplier(share.getSupplier());
        }
        entity.setProductType(share.getProductType());
        entity.setWarehouseName(share.getWarehouse().getName());
        entity.setEmployeeName(share.getEmployee().getName());
        entity.setInputDate(dto.getInputDate());
        entity.setStatus(dto.getStatus());
        entity.setDocumentNumber(dto.getDocumentNumber());
        entity.setDocumentDate(dto.getDocumentDate());
        entity.setContainerNumber(dto.getContainerNumber());
        entity.setSealNumber(dto.getSealNumber());
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNote(dto.getNote());

        if (CollectionUtils.isNotEmpty(dto.getItems())) {
            List<WhImportItem> items = new ArrayList<>();
            for (WhImportItemReqDto itemDto : dto.getItems()) {
                WhImportItem itemEntity = new WhImportItem();
                this.mapBasicDataToWhImportItem(itemDto, itemEntity, share);
                itemEntity.setParent(entity);
                items.add(itemEntity);
            }
            entity.setItems(items);
        }
    }

    private void mapBasicDataToWhImportItem(@NonNull WhImportItemReqDto dto, @NonNull WhImportItem entity, @NonNull WhImportShare share) {
        Product product = share.getProducts().get(dto.getProductId());
        entity.setProduct(product);

        ProductUnitConnect targetUnit = share.getUnits().get(dto.getUnitTargetId());
        entity.setUnitTarget(targetUnit);

        ProductUnitConnect sourceUnit = share.getUnits().get(dto.getUnitSourceId());
        entity.setUnitSource(sourceUnit);

        entity.setProductName(product.getName());
        entity.setConsignmentNumber(dto.getConsignmentNumber());
        if (entity.getConsignmentNumber() == null) {
            entity.setConsignmentNumber(Variables.NO_CONSIGNMENT);
        }
        entity.setQuantityTarget(dto.getQuantityTarget());

        ProductDetail productDetail = share.getProductDetails().get(dto.getProductDetailId());
        entity.setProductDetail(productDetail);
        entity.setPrice(productDetail.getPrice());

        Double sourceQuantity = (dto.getQuantityTarget() / targetUnit.getRatio()) * sourceUnit.getRatio();
        entity.setQuantitySource(sourceQuantity);

        if (CollectionUtils.isNotEmpty(dto.getDetails())) {
            List<WhImportItemDetail> details = new ArrayList<>();
            for (WhImportItemDetailReqDto detailDto : dto.getDetails()) {
                WhImportItemDetail detailEntity = new WhImportItemDetail();
                this.mapBasicDataToWhImportItemDetail(detailDto, detailEntity, share);
                detailEntity.setParent(entity);
                details.add(detailEntity);
            }
            entity.setDetails(details);
        }
    }

    private void mapBasicDataToWhImportItemDetail(@NonNull WhImportItemDetailReqDto dto, @NonNull WhImportItemDetail entity, @NonNull WhImportShare share) {
        WarehouseFloor floor = share.getFloors().get(dto.getFloorId());
        entity.setFloor(floor);
        entity.setFloorName(floor.getName());
        entity.setQuantity(dto.getQuantity());
    }

    private String generateCode() {
        String maxCode = whImportRepository.getMaxCode();
        if (StringUtils.isEmpty(maxCode)) {
            maxCode = "0";
        }
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        return String.format(EbsSystem.WH_FORMAT_TRANSACTIONS_CODE, EbsSystem.WH_IMPORT_PREFIX, currentYear, currentMonth, Integer.parseInt(maxCode) + 1);
    }

    private void mapProductToFloor(@NonNull WhImport entity) {
        LocalDateTime inputDate = entity.getInputDate();
        for (WhImportItem item : entity.getItems()) {
            Product product = item.getProduct();
            ProductUnitConnect unitTarget = item.getUnitTarget();
            ProductUnitConnect unitSource = item.getUnitSource();
            ProductDetail productDetail = item.getProductDetail();
            for (WhImportItemDetail detail : item.getDetails()) {
                WarehouseFloor floor = detail.getFloor();
                String consignment = item.getConsignmentNumber();

                ProductFloorSearchDto searchDto = ProductFloorSearchDto.builder()
                        .floorId(floor.getId())
                        .productId(product.getId())
                        .consignment(consignment)
                        .inputDate(inputDate)
                        .unitTargetId(unitTarget.getId())
                        .productDetailId(productDetail.getId())
                        .build();

                ProductFloor productFloor = productFloorRepository.search(searchDto);

                if (productFloor == null) {
                    productFloor = new ProductFloor();
                    productFloor.setProduct(product);
                    productFloor.setFloor(floor);
                    productFloor.setUnitTarget(unitTarget);
                    productFloor.setUnitSource(unitSource);
                    productFloor.setProductDetail(productDetail);
                    productFloor.setConsignment(consignment);
                    if (productFloor.getConsignment() == null) {
                        productFloor.setConsignment(Variables.NO_CONSIGNMENT);
                    }

                    Double detailQuantityTarget = detail.getQuantity();
                    Double detailQuantitySource = (detailQuantityTarget / unitTarget.getRatio()) * unitSource.getRatio();

                    productFloor.setQuantityTarget(detailQuantityTarget);
                    productFloor.setQuantitySource(detailQuantitySource);
                    productFloor.setInputDate(inputDate);
                } else {
                    Double detailQuantityTarget = detail.getQuantity();
                    Double newQuantityTarget = productFloor.getQuantityTarget() + detailQuantityTarget;
                    Double detailQuantitySource = (detailQuantityTarget / unitTarget.getRatio()) * unitSource.getRatio();
                    Double newQuantitySource = productFloor.getQuantitySource() + detailQuantitySource;

                    productFloor.setQuantityTarget(newQuantityTarget);
                    productFloor.setQuantitySource(newQuantitySource);
                }

                productFloorRepository.save(productFloor);
            }
        }
    }
}
