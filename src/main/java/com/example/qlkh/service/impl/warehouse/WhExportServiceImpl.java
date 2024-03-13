package com.example.qlkh.service.impl.warehouse;

import com.example.qlkh.constant.EbsSystem;
import com.example.qlkh.constant.Variables;
import com.example.qlkh.constant.enums.WhTransactionStatus;
import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.request.searchs.ProductFloorSearchDto;
import com.example.qlkh.dto.request.searchs.WhExportSearchReqDto;
import com.example.qlkh.dto.request.warehouse.exports.WhExportItemDetailReqDto;
import com.example.qlkh.dto.request.warehouse.exports.WhExportItemReqDto;
import com.example.qlkh.dto.request.warehouse.exports.WhExportReqDto;
import com.example.qlkh.dto.response.warehouse.exports.WhExportRespDto;
import com.example.qlkh.dto.share.WhExportShare;
import com.example.qlkh.entity.Employee;
import com.example.qlkh.entity.product.*;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.entity.warehouse.WarehouseFloor;
import com.example.qlkh.entity.warehouse.exports.WhExport;
import com.example.qlkh.entity.warehouse.exports.WhExportItem;
import com.example.qlkh.entity.warehouse.exports.WhExportItemDetail;
import com.example.qlkh.error.status.warehouse.WhExportStatus;
import com.example.qlkh.error.status.warehouse.WhImportStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.repository.EmployeeRepository;
import com.example.qlkh.repository.products.*;
import com.example.qlkh.repository.warehouse.WarehouseFloorRepository;
import com.example.qlkh.repository.warehouse.WarehouseRepository;
import com.example.qlkh.repository.warehouse.exports.WhExportItemRepository;
import com.example.qlkh.repository.warehouse.exports.WhExportRepository;
import com.example.qlkh.service.mapper.warehouse.exports.WhExportMapper;
import com.example.qlkh.service.warehouse.WhExportService;
import com.example.qlkh.utils.EbsSecurityUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WhExportServiceImpl implements WhExportService {
    private final WhExportRepository whExportRepository;
    private final WhExportItemRepository whExportItemRepository;
    private final WarehouseRepository warehouseRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductRepository productRepository;
    private final ProductUnitConnectRepository productUnitConnectRepository;
    private final ProductDetailRepository productDetailRepository;
    private final WarehouseFloorRepository warehouseFloorRepository;
    private final ProductFloorRepository productFloorRepository;
    private final WhExportMapper whExportMapper;

    @Override
    public WhExportRespDto getById(@NonNull Long id) {
        WhExport entity = whExportRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(WhExportStatus.ID_NOT_FOUND));
        return whExportMapper.mapEntityToDto(entity);
    }

    @Override
    public Page<WhExportRespDto> search(@NonNull WhExportSearchReqDto dto) {
        EbsPrincipal principal = EbsSecurityUtils.getCurrentUser();
        List<WhExport> whExports = whExportRepository.search(dto, principal);
        List<WhExportRespDto> content = whExports.stream()
                .map(e -> whExportMapper.mapEntityToDto(e, null))
                .collect(Collectors.toList());
        long total = whExportRepository.count(dto, principal);
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Transactional
    @Override
    public WhExportRespDto create(@NonNull WhExportReqDto dto) {
        dto.setId(null);
        WhExportShare share = new WhExportShare();
        this.validateWhExportDtoAndSetDataShare(dto, share);

        WhExport entity = new WhExport();
        this.mapBasicDataToWhExport(dto, entity, share);
        entity = whExportRepository.save(entity);

        if (WhTransactionStatus.FINISH.equals(entity.getStatus())) {
            this.mapProductToFloor(entity);
        }

        return whExportMapper.mapEntityToDto(entity);
    }

    @Transactional
    @Override
    public WhExportRespDto update(@NonNull Long id, @NonNull WhExportReqDto dto) {
        WhExport entity = whExportRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(WhExportStatus.ID_NOT_FOUND));

        if (WhTransactionStatus.FINISH.equals(entity.getStatus())) {
            throw new DataRuntimeException(WhExportStatus.HAS_FINISH);
        }

        dto.setId(id);
        WhExportShare share = new WhExportShare();
        this.validateWhExportDtoAndSetDataShare(dto, share);
        whExportItemRepository.deleteByParentId(id);
        this.mapBasicDataToWhExport(dto, entity, share);

        entity = whExportRepository.save(entity);

        if (WhTransactionStatus.FINISH.equals(entity.getStatus())) {
            this.mapProductToFloor(entity);
        }

        return whExportMapper.mapEntityToDto(entity);
    }

    @Transactional
    @Override
    public Boolean deleteById(@NonNull Long id) {
        WhExport entity = whExportRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(WhExportStatus.ID_NOT_FOUND));

        if (WhTransactionStatus.FINISH.equals(entity.getStatus())) {
            throw new DataRuntimeException(WhExportStatus.HAS_FINISH);
        }

        whExportRepository.delete(entity);
        return true;
    }

    private void validateWhExportDtoAndSetDataShare(@NonNull WhExportReqDto dto, @NonNull WhExportShare share) {
//        if (dto.getType() == null) {
//            throw new DataRuntimeException(WhExportStatus.TYPE_IS_NULL);
//        }

        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() -> new DataRuntimeException(WhExportStatus.WAREHOUSE_ID_NOT_FOUND));
        share.setWarehouse(warehouse);

        // sau này sẽ xóa vì lấy theo tài khoàn đăng nhập
        String username = EbsSecurityUtils.getUsername();

        Employee employee = employeeRepository.getByUsername(username);
        if (employee == null) {
            throw new DataRuntimeException(WhExportStatus.EMPLOYEE_ID_NOT_FOUND);
        }
        share.setEmployee(employee);

        ProductType productType = productTypeRepository.findById(dto.getProductTypeId())
                .orElseThrow(() -> new DataRuntimeException(WhExportStatus.PRODUCT_TYPE_ID_NOT_FOUND));
        share.setProductType(productType);

        if (dto.getInputDate() == null) {
            throw new DataRuntimeException(WhExportStatus.INPUT_DATE_IS_NULL);
        }

        if (dto.getStatus() == null) {
            throw new DataRuntimeException(WhExportStatus.STATUS_IS_NULL);
        }

        if (CollectionUtils.isNotEmpty(dto.getItems())) {
            for (WhExportItemReqDto itemDto : dto.getItems()) {
                this.validateWhExportItemDtoAndSetDataShare(itemDto, share);
            }
        }
    }

    private void validateWhExportItemDtoAndSetDataShare(@NonNull WhExportItemReqDto dto, @NonNull WhExportShare share) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new DataRuntimeException(WhExportStatus.PRODUCT_ID_NOT_FOUND));
        share.getProducts().put(dto.getProductId(), product);

        ProductUnitConnect targetUnit = productUnitConnectRepository.findById(dto.getUnitTargetId())
                .orElseThrow(() -> new DataRuntimeException(WhExportStatus.UNIT_TARGET_ID_NOT_FOUND));
        share.getUnits().put(dto.getUnitTargetId(), targetUnit);

        ProductDetail productDetail = productDetailRepository.findById(dto.getProductDetailId())
                .orElseThrow(() -> new DataRuntimeException(WhExportStatus.PRODUCT_DETAIL_ID_NOT_FOUND));
        share.getProductDetails().put(dto.getProductDetailId(), productDetail);

        ProductUnitConnect sourceUnit = productUnitConnectRepository.getDefaultByProductId(dto.getProductId());
        if (sourceUnit == null) {
            throw new DataRuntimeException(WhExportStatus.PRODUCT_NOT_HAS_UNIT_SOURCE_NOT_EXIST);
        }
        share.getUnits().put(sourceUnit.getId(), sourceUnit);
        dto.setUnitSourceId(sourceUnit.getId());

        if (dto.getQuantityTarget() == null) {
            throw new DataRuntimeException(WhExportStatus.QUANTITY_TARGET_IS_NULL);
        }

        if (dto.getQuantityTarget() <= Variables.MIN_QUANTITY) {
            throw new DataRuntimeException(WhExportStatus.QUANTITY_TARGET_WRONG_FORMAT);
        }

        String consignment = Variables.NO_CONSIGNMENT_MESSAGE.equals(dto.getConsignmentNumber()) ? Variables.NO_CONSIGNMENT : dto.getConsignmentNumber();
        ProductFloorSearchDto searchDto = ProductFloorSearchDto.builder()
                .productId(product.getId())
                .unitTargetId(targetUnit.getId())
                .productDetailId(productDetail.getId())
                .consignment(consignment)
                .build();

        this.validateWhExportItemDetailsDtoAndSetDataShare(dto.getQuantityTarget(), dto.getDetails(), searchDto, share);
    }

    private void validateWhExportItemDetailsDtoAndSetDataShare(@NonNull Double quantityTarget, List<WhExportItemDetailReqDto> dtos, @NonNull ProductFloorSearchDto searchDto, @NonNull WhExportShare share) {
        if (CollectionUtils.isEmpty(dtos)) {
            return;
        }

        double totalDetailQuantity = 0;
        for (WhExportItemDetailReqDto detailDto : dtos) {
            this.validateWhExportItemDetailDtoAndSetDataShare(detailDto, share);
            totalDetailQuantity += detailDto.getQuantity();
            searchDto.setFloorId(detailDto.getFloorId());
            searchDto.setInputDate(detailDto.getInputDate());
            this.validateProductQuantity(detailDto.getQuantity(), searchDto);
        }

        // validate tổng số ở itemDetail phải bằng với item
        if (Math.abs(quantityTarget - totalDetailQuantity) > Variables.DOUBLE_SAME) {
            throw new DataRuntimeException(WhExportStatus.QUANTITY_TARGET_NO_SAME_QUANTITY_DETAIL);
        }
    }

    private void validateProductQuantity(@NonNull Double requestQuantity, @NonNull ProductFloorSearchDto searchDto) {
        Double currentQuantity = productFloorRepository.getProductQuantity(searchDto);
        if (currentQuantity == null) {
            throw new DataRuntimeException(WhExportStatus.NOT_HAVE_PRODUCT);
        }

        if (currentQuantity - requestQuantity < 0) {
            throw new DataRuntimeException(WhExportStatus.QUANTITY_IS_NOT_ENOUGH);
        }
    }

    private void validateWhExportItemDetailDtoAndSetDataShare(@NonNull WhExportItemDetailReqDto dto, @NonNull WhExportShare share) {
        WarehouseFloor floor = warehouseFloorRepository.findById(dto.getFloorId())
                .orElseThrow(() -> new DataRuntimeException(WhExportStatus.FLOOR_ID_IS_NULL));
        share.getFloors().put(dto.getFloorId(), floor);

        if (dto.getInputDate() == null) {
            throw new DataRuntimeException(WhExportStatus.INPUT_DATE_IS_NULL);
        }

        if (dto.getQuantity() == null) {
            throw new DataRuntimeException(WhExportStatus.QUANTITY_DETAIL_IS_NULL);
        }

        if (dto.getQuantity() <= Variables.MIN_QUANTITY) {
            throw new DataRuntimeException(WhExportStatus.QUANTITY_DETAIL_WRONG_FORMAT);
        }
    }

    private void mapBasicDataToWhExport(@NonNull WhExportReqDto dto, @NonNull WhExport entity, @NonNull WhExportShare share) {
        if (dto.getId() == null) {
            entity.setCode(this.generateCode());
        }
        entity.setWarehouse(share.getWarehouse());
        entity.setEmployee(share.getEmployee());
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
        entity.setQuality(dto.getQuality());
        entity.setConsumerName(dto.getConsumerName());
        entity.setNote(dto.getNote());

        if (CollectionUtils.isNotEmpty(dto.getItems())) {
            List<WhExportItem> items = new ArrayList<>();
            for (WhExportItemReqDto itemDto : dto.getItems()) {
                WhExportItem itemEntity = new WhExportItem();
                this.mapBasicDataToWhExportItem(itemDto, itemEntity, share);
                itemEntity.setParent(entity);
                items.add(itemEntity);
            }
            entity.setItems(items);
        }
    }

    private void mapBasicDataToWhExportItem(@NonNull WhExportItemReqDto dto, @NonNull WhExportItem entity, @NonNull WhExportShare share) {
        Product product = share.getProducts().get(dto.getProductId());
        entity.setProduct(product);

        ProductUnitConnect targetUnit = share.getUnits().get(dto.getUnitTargetId());
        entity.setUnitTarget(targetUnit);

        ProductUnitConnect sourceUnit = share.getUnits().get(dto.getUnitSourceId());
        entity.setUnitSource(sourceUnit);

        entity.setConsignmentNumber(dto.getConsignmentNumber());
        if (Variables.NO_CONSIGNMENT_MESSAGE.equals(dto.getConsignmentNumber()) || entity.getConsignmentNumber() == null) {
            entity.setConsignmentNumber(Variables.NO_CONSIGNMENT);
        }
        entity.setProductName(product.getName());
        entity.setQuantityTarget(dto.getQuantityTarget());

        ProductDetail productDetail = share.getProductDetails().get(dto.getProductDetailId());
        entity.setProductDetail(productDetail);
        entity.setPrice(productDetail.getPrice());

        Double sourceQuantity = (dto.getQuantityTarget() / targetUnit.getRatio()) * sourceUnit.getRatio();
        entity.setQuantitySource(sourceQuantity);


        if (CollectionUtils.isNotEmpty(dto.getDetails())) {
            List<WhExportItemDetail> details = new ArrayList<>();
            for (WhExportItemDetailReqDto detailDto : dto.getDetails()) {
                WhExportItemDetail detailEntity = new WhExportItemDetail();
                this.mapBasicDataToWhExportItemDetail(detailDto, detailEntity, share);
                detailEntity.setParent(entity);
                details.add(detailEntity);
            }
            entity.setDetails(details);
        }
    }

    private void mapBasicDataToWhExportItemDetail(@NonNull WhExportItemDetailReqDto dto, @NonNull WhExportItemDetail entity, @NonNull WhExportShare share) {
        WarehouseFloor floor = share.getFloors().get(dto.getFloorId());
        entity.setFloor(floor);
        entity.setFloorName(floor.getName());
        entity.setQuantity(dto.getQuantity());
        entity.setInputDate(dto.getInputDate());
    }

    private String generateCode() {
        String maxCode = whExportRepository.getMaxCode();
        if (StringUtils.isEmpty(maxCode)) {
            maxCode = "0";
        }
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        return String.format(EbsSystem.WH_FORMAT_TRANSACTIONS_CODE, EbsSystem.WH_EXPORT_PREFIX, currentYear, currentMonth, Integer.parseInt(maxCode) + 1);
    }

    private void mapProductToFloor(@NonNull WhExport entity) {
        for (WhExportItem item : entity.getItems()) {
            Product product = item.getProduct();
            ProductUnitConnect unitTarget = item.getUnitTarget();
            ProductUnitConnect unitSource = item.getUnitSource();
            ProductDetail productDetail = item.getProductDetail();
            for (WhExportItemDetail detail : item.getDetails()) {
                WarehouseFloor floor = detail.getFloor();
                String consignment = item.getConsignmentNumber();

                ProductFloorSearchDto searchDto = ProductFloorSearchDto.builder()
                        .floorId(floor.getId())
                        .productId(product.getId())
                        .consignment(consignment)
                        .inputDate(detail.getInputDate())
                        .unitTargetId(unitTarget.getId())
                        .productDetailId(productDetail.getId())
                        .build();
                ProductFloor productFloor = productFloorRepository.search(searchDto);

                if (productFloor == null) {
                    throw new DataRuntimeException(WhExportStatus.QUANTITY_IS_NOT_ENOUGH);
                }

                Double detailQuantityTarget = detail.getQuantity();
                Double newQuantityTarget = productFloor.getQuantityTarget() - detailQuantityTarget;
                Double detailQuantitySource = (detailQuantityTarget / unitTarget.getRatio()) * unitSource.getRatio();
                Double newQuantitySource = productFloor.getQuantitySource() - detailQuantitySource;

                productFloor.setQuantityTarget(newQuantityTarget);
                productFloor.setQuantitySource(newQuantitySource);

                productFloorRepository.save(productFloor);
            }
        }
    }
}
