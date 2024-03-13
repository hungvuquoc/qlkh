package com.example.qlkh.service.impl.warehouse;

import com.example.qlkh.constant.AuthorityConstant;
import com.example.qlkh.constant.EbsSystem;
import com.example.qlkh.constant.Variables;
import com.example.qlkh.constant.enums.WhItemDetailType;
import com.example.qlkh.constant.enums.WhTransactionStatus;
import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.request.searchs.ProductFloorSearchDto;
import com.example.qlkh.dto.request.searchs.WhTransferSearchReqDto;
import com.example.qlkh.dto.request.warehouse.transfers.WhTransferItemDetailReqDto;
import com.example.qlkh.dto.request.warehouse.transfers.WhTransferItemReqDto;
import com.example.qlkh.dto.request.warehouse.transfers.WhTransferReqDto;
import com.example.qlkh.dto.response.warehouse.transfers.WhTransferRespDto;
import com.example.qlkh.dto.share.WhTransferShare;
import com.example.qlkh.entity.Employee;
import com.example.qlkh.entity.product.*;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.entity.warehouse.WarehouseFloor;
import com.example.qlkh.entity.warehouse.WarehouseLocation;
import com.example.qlkh.entity.warehouse.transfers.WhTransfer;
import com.example.qlkh.entity.warehouse.transfers.WhTransferItem;
import com.example.qlkh.entity.warehouse.transfers.WhTransferItemDetail;
import com.example.qlkh.error.status.warehouse.WhTransferStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.repository.EmployeeRepository;
import com.example.qlkh.repository.products.*;
import com.example.qlkh.repository.warehouse.WarehouseFloorRepository;
import com.example.qlkh.repository.warehouse.WarehouseRepository;
import com.example.qlkh.repository.warehouse.transfers.WhTransferItemDetailRepository;
import com.example.qlkh.repository.warehouse.transfers.WhTransferItemRepository;
import com.example.qlkh.repository.warehouse.transfers.WhTransferRepository;
import com.example.qlkh.service.mapper.warehouse.transfers.WhTransferMapper;
import com.example.qlkh.service.warehouse.WhTransferService;
import com.example.qlkh.utils.EbsSecurityUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WhTransferServiceImpl implements WhTransferService {
    private final WhTransferRepository whTransferRepository;
    private final WhTransferItemRepository whTransferItemRepository;
    private final WhTransferItemDetailRepository whTransferItemDetailRepository;
    private final WarehouseRepository warehouseRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductRepository productRepository;
    private final ProductUnitConnectRepository productUnitConnectRepository;
    private final ProductDetailRepository productDetailRepository;
    private final WarehouseFloorRepository warehouseFloorRepository;
    private final ProductFloorRepository productFloorRepository;
    private final WhTransferMapper whTransferMapper;

    @Override
    public WhTransferRespDto getById(@NonNull Long id) {
        WhTransfer entity = whTransferRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(WhTransferStatus.ID_NOT_FOUND));
        return whTransferMapper.mapEntityToDto(entity, entity.getItems());
    }

    @Override
    public Page<WhTransferRespDto> search(@NonNull WhTransferSearchReqDto dto) {
        EbsPrincipal principal = EbsSecurityUtils.getCurrentUser();
        List<WhTransfer> whTransfers = whTransferRepository.search(dto, principal);
        List<WhTransferRespDto> content = whTransfers.stream()
                .map(whTransferMapper::mapEntityToDto)
                .collect(Collectors.toList());
        long total = whTransferRepository.count(dto, principal);
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Transactional(rollbackOn = {Exception.class})
    @Override
    public WhTransferRespDto create(@NonNull WhTransferReqDto dto) {
        dto.setId(null);
        WhTransferShare share = new WhTransferShare();
        WhTransfer entity = new WhTransfer();

        this.validateDto(dto, entity, share);
        this.mapEntity(dto, entity, share);

        entity = whTransferRepository.save(entity);

        this.mapProductToFloor(entity, share);

        return whTransferMapper.mapEntityToDto(entity, entity.getItems());
    }

    @Transactional(rollbackOn = {Exception.class})
    @Override
    public WhTransferRespDto update(@NonNull Long id, @NonNull WhTransferReqDto dto) {
        dto.setId(id);
        WhTransfer entity = whTransferRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(WhTransferStatus.ID_NOT_FOUND));

        if (WhTransactionStatus.FINISH.equals(entity.getStatus())) {
            throw new DataRuntimeException(WhTransferStatus.HAS_FINISH);
        }

        WhTransferShare share = new WhTransferShare();

        this.validateDto(dto, entity, share);
        this.deleteItem(id, share);
        this.mapEntity(dto, entity, share);

        entity = whTransferRepository.save(entity);

        this.mapProductToFloor(entity, share);

        return whTransferMapper.mapEntityToDto(entity, entity.getItems());
    }

    @Override
    public Boolean deleteById(@NonNull Long id) {
        WhTransfer entity = whTransferRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(WhTransferStatus.ID_NOT_FOUND));

        if (!WhTransactionStatus.FINISH.equals(entity.getStatus())) {
            throw new DataRuntimeException(WhTransferStatus.HAS_FINISH);
        }

        whTransferRepository.delete(entity);
        return true;
    }


    private void validateDto(@NonNull WhTransferReqDto dto, @NonNull WhTransfer entity, @NonNull WhTransferShare share) {
        Warehouse warehouseImport = warehouseRepository.findById(dto.getWarehouseImportId())
                .orElseThrow(() -> new DataRuntimeException(WhTransferStatus.WAREHOUSE_IMPORT_ID_NOT_FOUND));
        share.setWarehouseImport(warehouseImport);

        Warehouse warehouseExport = warehouseRepository.findById(dto.getWarehouseExportId())
                .orElseThrow(() -> new DataRuntimeException(WhTransferStatus.WAREHOUSE_EXPORT_ID_NOT_FOUND));
        share.setWarehouseExport(warehouseExport);

        this.validatePermission(dto, share);

        EbsPrincipal userPrincipal = EbsSecurityUtils.getCurrentUser();
        share.setUserPrincipal(userPrincipal);

        Employee employee = employeeRepository.getByUsername(userPrincipal.getUsername());
        if (employee == null) {
            throw new DataRuntimeException(WhTransferStatus.EMPLOYEE_ID_NOT_FOUND);
        }
        share.setEmployee(employee);

        ProductType productType = productTypeRepository.findById(dto.getProductTypeId())
                .orElseThrow(() -> new DataRuntimeException(WhTransferStatus.PRODUCT_TYPE_ID_NOT_FOUND));
        share.setProductType(productType);

        if (dto.getInputDate() == null) {
            throw new DataRuntimeException(WhTransferStatus.INPUT_DATE_IS_NULL);
        }
        share.setInputDate(dto.getInputDate());

        if (dto.getStatus() == null) {
            throw new DataRuntimeException(WhTransferStatus.STATUS_IS_NULL);
        }
        share.setStatus(dto.getStatus());

        this.validateItemDtos(dto, entity, share);
    }

    private void validatePermission(@NonNull WhTransferReqDto dto, @NonNull WhTransferShare share) {
        boolean isFinishStatus = WhTransactionStatus.FINISH.equals(share.getStatus());

        boolean hasExportPermission = EbsSecurityUtils.anyWhPermission(dto.getWarehouseExportId(),
                AuthorityConstant.WH_IMPORT_VIEW_MY_CREATE, AuthorityConstant.WH_IMPORT_UPDATE_MY_CREATE);
        share.setExportPermission(hasExportPermission);

        boolean hasImportPermission = EbsSecurityUtils.anyWhPermission(dto.getWarehouseExportId(),
                AuthorityConstant.WH_IMPORT_VIEW_MY_CREATE, AuthorityConstant.WH_IMPORT_UPDATE_MY_CREATE);
        share.setImportPermission(hasImportPermission);

        if (hasExportPermission && !hasImportPermission && isFinishStatus) {
            throw new DataRuntimeException(WhTransferStatus.WH_NOT_HAS_FINISH_PERMISSION);
        }

        if (hasImportPermission && !hasExportPermission && !isFinishStatus) {
            throw new DataRuntimeException(WhTransferStatus.WH_EXPORT_UNFINISHED);
        }
    }

    private void validateItemDtos(@NonNull WhTransferReqDto dto, @NonNull WhTransfer entity, @NonNull WhTransferShare share) {

        boolean isInitStatus = WhTransactionStatus.INIT.equals(share.getStatus());
        if (!isInitStatus && CollectionUtils.isEmpty(dto.getItems())) {
            throw new DataRuntimeException(WhTransferStatus.ITEM_IS_EMPTY);
        }

        WhTransactionStatus oldStatus = entity.getStatus();
        if (!share.isFullPermission() && WhTransactionStatus.FINISH_EXPORT.equals(oldStatus)) {
            if (dto.getItems().size() != entity.getItems().size()) {
                throw new DataRuntimeException(WhTransferStatus.ITEM_IMPORT_NOT_SAME_ITEM_EXPORT);
            }
            this.equalsItems(dto.getItems(), entity.getItems());
        }

        for (WhTransferItemReqDto itemDto : dto.getItems()) {
            validateItemDto(itemDto, share);
        }
    }

    private void equalsItems(@NonNull List<WhTransferItemReqDto> itemDtos, @NonNull List<WhTransferItem> itemEntities) {
        Map<String, Double> itemMap = itemEntities.stream().collect(Collectors.toMap(item -> {
            Long productId = item.getProduct().getId();
            Long unitTargetId = item.getUnitTarget().getId();
            Long productDetailId = item.getProductDetail().getId();
            String consignment = item.getConsignmentNumber();
            return String.format("%d-%d-%d-%s", productId, unitTargetId, productDetailId, consignment);
        }, WhTransferItem::getQuantityTarget));

        for (WhTransferItemReqDto itemDto : itemDtos) {
            Long productId = itemDto.getProductId();
            Long unitTargetId = itemDto.getUnitTargetId();
            Long productDetailId = itemDto.getProductDetailId();
            String consignment = itemDto.getConsignmentNumber();
            if (Variables.NO_CONSIGNMENT_MESSAGE.equals(consignment)) {
                consignment = Variables.NO_CONSIGNMENT;
            }
            String key = String.format("%d-%d-%d-%s", productId, unitTargetId, productDetailId, consignment);

            Double itemQuantity = itemMap.get(key);

            if (itemQuantity == null
                    || Math.abs(itemQuantity - itemDto.getQuantityTarget()) > Variables.DOUBLE_SAME) {
                throw new DataRuntimeException(WhTransferStatus.QUANTITY_ITEM_IMPORT_NOT_SAME_ITEM_EXPORT);
            }
        }
    }

    private void validateItemDto(@NonNull WhTransferItemReqDto dto, @NonNull WhTransferShare share) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new DataRuntimeException(WhTransferStatus.PRODUCT_ID_NOT_FOUND));
        share.getProducts().put(dto.getProductId(), product);

        ProductUnitConnect targetUnit = productUnitConnectRepository.findById(dto.getUnitTargetId())
                .orElseThrow(() -> new DataRuntimeException(WhTransferStatus.UNIT_TARGET_ID_NOT_FOUND));
        share.getUnits().put(dto.getUnitTargetId(), targetUnit);

        ProductDetail productDetail = productDetailRepository.findById(dto.getProductDetailId())
                .orElseThrow(() -> new DataRuntimeException(WhTransferStatus.PRODUCT_DETAIL_ID_NOT_FOUND));
        share.getProductDetails().put(dto.getProductDetailId(), productDetail);

        ProductUnitConnect sourceUnit = productUnitConnectRepository.getDefaultByProductId(dto.getProductId());
        if (sourceUnit == null) {
            throw new DataRuntimeException(WhTransferStatus.PRODUCT_NOT_HAS_UNIT_SOURCE_NOT_EXIST);
        }
        share.getUnits().put(sourceUnit.getId(), sourceUnit);
        dto.setUnitSourceId(sourceUnit.getId());

        if (dto.getQuantityTarget() == null) {
            throw new DataRuntimeException(WhTransferStatus.QUANTITY_TARGET_IS_NULL);
        }

        if (dto.getQuantityTarget() <= Variables.MIN_QUANTITY) {
            throw new DataRuntimeException(WhTransferStatus.QUANTITY_TARGET_WRONG_FORMAT);
        }

        String consignment = Variables.NO_CONSIGNMENT_MESSAGE.equals(dto.getConsignmentNumber()) ? Variables.NO_CONSIGNMENT : dto.getConsignmentNumber();
        ProductFloorSearchDto searchDto = ProductFloorSearchDto.builder()
                .productId(product.getId())
                .unitTargetId(targetUnit.getId())
                .productDetailId(productDetail.getId())
                .consignment(consignment)
                .build();

        this.validateItemDetailDtos(dto.getQuantityTarget(), dto.getDetails(), searchDto, share);
    }

    private void validateItemDetailDtos(@NonNull Double quantityTarget,
                                        List<WhTransferItemDetailReqDto> detailDtos,
                                        @NonNull ProductFloorSearchDto searchDto,
                                        @NonNull WhTransferShare share) {

        this.validateEmptyItemDetails(detailDtos, share);

        double totalDetailQuantityExport = 0;
        double totalDetailQuantityImport = 0;
        for (WhTransferItemDetailReqDto detailDto : detailDtos) {
            this.validateWhTransferItemDetail(detailDto, share);
            if (WhItemDetailType.EXPORT.equals(detailDto.getType())) {
                totalDetailQuantityExport += detailDto.getQuantity();
                searchDto.setFloorId(detailDto.getFloorId());
                searchDto.setInputDate(detailDto.getInputDate());
                this.validateProductQuantity(detailDto.getQuantity(), searchDto);
            } else {
                totalDetailQuantityImport += detailDto.getQuantity();
            }
        }

        if (share.isFullPermission()) {
            if (Math.abs(quantityTarget - totalDetailQuantityExport) > Variables.DOUBLE_SAME) {
                throw new DataRuntimeException(WhTransferStatus.QUANTITY_TARGET_NO_SAME_QUANTITY_ITEM_EXPORT_DETAIL);
            }
            if (Math.abs(quantityTarget - totalDetailQuantityImport) > Variables.DOUBLE_SAME) {
                throw new DataRuntimeException(WhTransferStatus.QUANTITY_TARGET_NO_SAME_QUANTITY_ITEM_IMPORT_DETAIL);
            }
            return;
        }

        boolean isFinishStatus = WhTransactionStatus.FINISH.equals(share.getStatus());
        if (share.isExportPermission()
                && !isFinishStatus
                && Math.abs(quantityTarget - totalDetailQuantityExport) > Variables.DOUBLE_SAME) {
            throw new DataRuntimeException(WhTransferStatus.QUANTITY_TARGET_NO_SAME_QUANTITY_ITEM_EXPORT_DETAIL);
        }

        if (share.isImportPermission()
                && isFinishStatus
                && Math.abs(quantityTarget - totalDetailQuantityImport) > Variables.DOUBLE_SAME) {
            throw new DataRuntimeException(WhTransferStatus.QUANTITY_TARGET_NO_SAME_QUANTITY_ITEM_IMPORT_DETAIL);
        }
    }

    private void validateEmptyItemDetails(List<WhTransferItemDetailReqDto> detailDtos,
                                          @NonNull WhTransferShare share) {
        boolean isFinishStatus = WhTransactionStatus.FINISH.equals(share.getStatus());

        List<WhTransferItemDetailReqDto> detailImportDtos = new ArrayList<>();
        List<WhTransferItemDetailReqDto> detailExportDtos = new ArrayList<>();

        for (WhTransferItemDetailReqDto detailDto : detailDtos) {
            if (WhItemDetailType.IMPORT.equals(detailDto.getType())) {
                detailImportDtos.add(detailDto);
                continue;
            }
            detailExportDtos.add(detailDto);
        }

        if (share.isFullPermission()) {
            if (CollectionUtils.isEmpty(detailExportDtos)) {
                throw new DataRuntimeException(WhTransferStatus.ITEM_DETAIL_EXPORT_IS_EMPTY);
            }
            if (CollectionUtils.isEmpty(detailImportDtos)) {
                throw new DataRuntimeException(WhTransferStatus.ITEM_DETAIL_IMPORT_IS_EMPTY);
            }
            return;
        }
        if (share.isExportPermission()
                && !isFinishStatus
                && CollectionUtils.isEmpty(detailExportDtos)) {
            throw new DataRuntimeException(WhTransferStatus.ITEM_DETAIL_EXPORT_IS_EMPTY);
        }

        if (share.isImportPermission()
                && isFinishStatus
                && CollectionUtils.isEmpty(detailImportDtos)) {
            throw new DataRuntimeException(WhTransferStatus.ITEM_DETAIL_IMPORT_IS_EMPTY);
        }
    }

    private void validateWhTransferItemDetail(@NonNull WhTransferItemDetailReqDto dto, @NonNull WhTransferShare share) {
        WarehouseFloor floor = warehouseFloorRepository.findById(dto.getFloorId())
                .orElseThrow(() -> new DataRuntimeException(WhTransferStatus.FLOOR_ID_IS_NULL));
        share.getFloors().put(dto.getFloorId(), floor);

        if (WhItemDetailType.EXPORT.equals(dto.getType()) && dto.getInputDate() == null) {
            throw new DataRuntimeException(WhTransferStatus.INPUT_DATE_IS_NULL);
        }

        if (dto.getQuantity() == null) {
            throw new DataRuntimeException(WhTransferStatus.QUANTITY_DETAIL_IS_NULL);
        }

        if (dto.getQuantity() <= Variables.MIN_QUANTITY) {
            throw new DataRuntimeException(WhTransferStatus.QUANTITY_DETAIL_WRONG_FORMAT);
        }
    }

    private void validateProductQuantity(@NonNull Double requestQuantity, @NonNull ProductFloorSearchDto searchDto) {
        Double currentQuantity = productFloorRepository.getProductQuantity(searchDto);
        if (currentQuantity == null) {
            throw new DataRuntimeException(WhTransferStatus.NOT_HAVE_PRODUCT);
        }

        if (currentQuantity - requestQuantity < 0) {
            throw new DataRuntimeException(WhTransferStatus.QUANTITY_IS_NOT_ENOUGH);
        }
    }

    private void mapEntity(@NonNull WhTransferReqDto dto, @NonNull WhTransfer entity, @NonNull WhTransferShare share) {

        if (dto.getId() == null) {
            entity.setCode(this.generateCode());
        }
        Warehouse warehouseImport = share.getWarehouseImport();
        entity.setWarehouseImport(warehouseImport);
        Warehouse warehouseExport = share.getWarehouseExport();
        entity.setWarehouseExport(warehouseExport);
        Employee employee = share.getEmployee();
        entity.setEmployee(share.getEmployee());
        entity.setProductType(share.getProductType());
        entity.setWarehouseImportName(warehouseImport.getName());
        entity.setWarehouseExportName(warehouseExport.getName());
        entity.setEmployeeName(employee.getName());
        entity.setStatus(dto.getStatus());
        entity.setInputDate(dto.getInputDate());
        entity.setDocumentNumber(dto.getDocumentNumber());
        entity.setDocumentDate(dto.getDocumentDate());
        entity.setNote(dto.getNote());

        if (CollectionUtils.isNotEmpty(dto.getItems())) {
            List<WhTransferItem> items = new ArrayList<>();
            for (WhTransferItemReqDto itemDto : dto.getItems()) {
                WhTransferItem itemEntity = new WhTransferItem();
                this.mapItemEntity(itemDto, itemEntity, share);
                itemEntity.setParent(entity);
                items.add(itemEntity);
            }
            entity.setItems(items);
        }
    }

    private void mapItemEntity(@NonNull WhTransferItemReqDto dto, @NonNull WhTransferItem entity, @NonNull WhTransferShare share) {
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

        this.mapItemDetails(dto, entity, share);
    }

    private void mapItemDetails(@NonNull WhTransferItemReqDto dto, @NonNull WhTransferItem entity, @NonNull WhTransferShare share) {
        if (CollectionUtils.isNotEmpty(dto.getDetails())) {
            List<WhTransferItemDetailReqDto> itemDetailDtos = this.getItemDetailsWithPermission(dto.getDetails(), share);
            List<WhTransferItemDetail> details = new ArrayList<>();
            for (WhTransferItemDetailReqDto detailDto : itemDetailDtos) {
                WhTransferItemDetail detailEntity = new WhTransferItemDetail();
                WarehouseFloor floor = share.getFloors().get(detailDto.getFloorId());
                detailEntity.setFloor(floor);
                detailEntity.setFloorName(floor.getName());
                detailEntity.setQuantity(detailDto.getQuantity());
                detailEntity.setType(detailDto.getType());
                if (WhItemDetailType.EXPORT.equals(detailDto.getType())) {
                    detailEntity.setInputDate(detailDto.getInputDate());
                } else {
                    detailEntity.setInputDate(share.getInputDate());
                }
                detailEntity.setParent(entity);
                details.add(detailEntity);
            }
            entity.setDetails(details);
        }
    }

    private List<WhTransferItemDetailReqDto> getItemDetailsWithPermission(@NonNull List<WhTransferItemDetailReqDto> itemDetailDtos, @NonNull WhTransferShare share) {
        if (share.isFullPermission()) {
            return itemDetailDtos;
        }

        boolean isFinishStatus = WhTransactionStatus.FINISH.equals(share.getStatus());
        if (share.isExportPermission() && !isFinishStatus) {
            return itemDetailDtos.stream().filter(itemDetail -> WhItemDetailType.EXPORT.equals(itemDetail.getType())).collect(Collectors.toList());
        }

        if (share.isImportPermission() && isFinishStatus) {
            return itemDetailDtos.stream().filter(itemDetail -> WhItemDetailType.IMPORT.equals(itemDetail.getType())).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    private String generateCode() {
        String maxCode = whTransferRepository.getMaxCode();
        if (StringUtils.isEmpty(maxCode)) {
            maxCode = "0";
        }
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        return String.format(EbsSystem.WH_FORMAT_TRANSACTIONS_CODE, EbsSystem.WH_TRANSFER_PREFIX, currentYear, currentMonth, Integer.parseInt(maxCode) + 1);
    }

    private void deleteItem(@NonNull Long whTransferId, @NonNull WhTransferShare share) {
        boolean isFinishStatus = WhTransactionStatus.FINISH.equals(share.getStatus());
        if (share.isFullPermission()) {
            whTransferItemRepository.deleteByParentId(whTransferId);
            return;
        }

        if (share.isExportPermission() && !isFinishStatus) {
            whTransferItemRepository.deleteByParentId(whTransferId);
        }

        if (share.isImportPermission() && isFinishStatus) {
            whTransferItemDetailRepository.deleteByWhTransferId(whTransferId);
        }
    }

    private void mapProductToFloor(@NonNull WhTransfer entity, @NonNull WhTransferShare share) {
        boolean isFinishStatus = WhTransactionStatus.FINISH.equals(share.getStatus());
        if (share.isFullPermission() && isFinishStatus) {
            this.mapProductExportToFloor(entity);
            this.mapProductImportToFloor(entity);
            return;
        }

        boolean isFinishExportStatus = WhTransactionStatus.FINISH_EXPORT.equals(share.getStatus());
        if (share.isExportPermission() && isFinishExportStatus) {
            this.mapProductExportToFloor(entity);
        }

        if (share.isImportPermission() && isFinishStatus) {
            this.mapProductImportToFloor(entity);
        }
    }

    private void mapProductExportToFloor(@NonNull WhTransfer entity) {
        for (WhTransferItem item : entity.getItems()) {
            Product product = item.getProduct();
            ProductUnitConnect unitTarget = item.getUnitTarget();
            ProductUnitConnect unitSource = item.getUnitSource();
            ProductDetail productDetail = item.getProductDetail();
            List<WhTransferItemDetail> details = item.getDetails().stream()
                    .filter(itemDetail -> WhItemDetailType.EXPORT.equals(itemDetail.getType())).collect(Collectors.toList());
            for (WhTransferItemDetail detail : details) {
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
                    throw new DataRuntimeException(WhTransferStatus.QUANTITY_IS_NOT_ENOUGH);
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

    private void mapProductImportToFloor(@NonNull WhTransfer entity) {
        LocalDateTime inputDate = entity.getInputDate();
        for (WhTransferItem item : entity.getItems()) {
            Product product = item.getProduct();
            ProductUnitConnect unitTarget = item.getUnitTarget();
            ProductUnitConnect unitSource = item.getUnitSource();
            ProductDetail productDetail = item.getProductDetail();
            List<WhTransferItemDetail> details = item.getDetails().stream()
                    .filter(itemDetail -> WhItemDetailType.IMPORT.equals(itemDetail.getType())).collect(Collectors.toList());
            for (WhTransferItemDetail detail : details) {
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
