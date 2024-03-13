package com.example.qlkh.service.impl.warehouse;

import com.example.qlkh.constant.EbsSystem;
import com.example.qlkh.constant.Variables;
import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.request.searchs.*;
import com.example.qlkh.dto.request.warehouse.WarehouseAreaReqDto;
import com.example.qlkh.dto.request.warehouse.WarehouseLocationReqDto;
import com.example.qlkh.dto.request.warehouse.WarehouseReqDto;
import com.example.qlkh.dto.response.products.ProductDetailRespDto;
import com.example.qlkh.dto.response.products.ProductRespDto;
import com.example.qlkh.dto.response.products.ProductTypeRespDto;
import com.example.qlkh.dto.response.projection.*;
import com.example.qlkh.dto.response.warehouse.CardRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseAreaRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseLocationRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseRespDto;
import com.example.qlkh.entity.product.Product;
import com.example.qlkh.entity.product.ProductDetail;
import com.example.qlkh.entity.product.ProductType;
import com.example.qlkh.entity.warehouse.*;
import com.example.qlkh.error.status.warehouse.WarehouseStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.repository.products.*;
import com.example.qlkh.repository.warehouse.*;
import com.example.qlkh.service.mapper.products.ProductDetailMapper;
import com.example.qlkh.service.mapper.products.ProductMapper;
import com.example.qlkh.service.mapper.products.ProductTypeMapper;
import com.example.qlkh.service.mapper.warehouse.WarehouseAreaMapper;
import com.example.qlkh.service.mapper.warehouse.WarehouseLocationMapper;
import com.example.qlkh.service.mapper.warehouse.WarehouseMapper;
import com.example.qlkh.service.warehouse.WarehouseService;
import com.example.qlkh.utils.EbsCollectionsUtils;
import com.example.qlkh.utils.EbsConvertUtils;
import com.example.qlkh.utils.EbsSecurityUtils;
import com.example.qlkh.utils.EbsStringUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseStructureRepository warehouseStructureRepository;
    private final WarehouseAreaRepository warehouseAreaRepository;
    private final WarehouseFloorRepository warehouseFloorRepository;
    private final WarehouseLocationRepository warehouseLocationRepository;
    private final WarehouseProductTypeConnectRepository warehouseProductTypeConnectRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductRepository productRepository;
    private final ProductUnitConnectRepository productUnitConnectRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ProductFloorRepository productFloorRepository;
    private final WarehouseMapper warehouseMapper;
    private final WarehouseAreaMapper warehouseAreaMapper;
    private final WarehouseLocationMapper warehouseLocationMapper;
    private final ProductTypeMapper productTypeMapper;
    private final ProductMapper productMapper;
    private final ProductDetailMapper productDetailMapper;

    @Override
    public List<String> getMapPointHasProductByWarehouseId(@NonNull Long warehouseId) {
        return warehouseLocationRepository.getMapPointHasProductByWarehouseId(warehouseId);
    }

    @Override
    public List<String> getMapPointHasProductBy(@NonNull StockSearchDto dto) {
        return warehouseLocationRepository.getMapPointByProductFloorId(dto);
    }

    @Override
    public List<ProductTypeRespDto> getProductTypeInStock(@NonNull StockSearchDto dto) {
        List<ProductType> productTypes = productTypeRepository.getInStock(dto);
        return productTypes.stream().map(productTypeMapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductRespDto> getProductInStock(@NonNull StockSearchDto dto) {
        List<Product> products = productRepository.getInStock(dto);
        return products.stream().map(productMapper::mapEntityToDtoHidden).collect(Collectors.toList());
    }

    @Override
    public List<ProductUnitProjectionDto> getUnitInStockBy(@NonNull StockSearchDto dto) {
        return productUnitConnectRepository.getInStock(dto);
    }

    @Override
    public List<ProductDetailRespDto> getProductDetailInStockBy(@NonNull StockSearchDto dto) {
        List<ProductDetail> details = productDetailRepository.getInStock(dto);
        return details.stream().map(productDetailMapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<String> getConsignmentInStockBy(@NonNull StockSearchDto dto) {
        return productFloorRepository.getConsignmentInStock(dto);
    }

    @Override
    public List<ProductFloorProjectionDto> getFloorDetailBy(@NonNull StockSearchDto dto) {
        return productFloorRepository.getInStock(dto);
    }

    @Override
    public List<ItemDto> getQuantityInFloor(@NonNull List<ItemDto> itemDtos) {
        for (ItemDto itemDto : itemDtos) {
            this.mapQuantityToItem(itemDto);
        }
        return itemDtos;
    }

    @Override
    public WarehouseRespDto getById(@NonNull Long id, LocalDateTime modifyDate) {
        log.info("WarehouseServiceImpl method getById, id: {}", id);
        Warehouse entity = warehouseRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(WarehouseStatus.ID_NOT_FOUND));

        List<WarehouseLocation> locations = warehouseLocationRepository.getLocationsWithWarehouseIdAndModifyDate(id, modifyDate);

        Map<WarehouseArea, List<WarehouseLocation>> areaMap = locations.stream().collect(Collectors.groupingBy(WarehouseLocation::getWarehouseArea));
        List<WarehouseAreaRespDto> areaDtos = new ArrayList<>();
        for (WarehouseArea area : areaMap.keySet()) {
            WarehouseAreaRespDto areaDto = warehouseAreaMapper.mapEntityToDto(area, areaMap.get(area));
            Map<String, WarehouseLocationRespDto> locationDtoMap = areaDto.getLocations();
            int maxNumberOfFloors = 0;
            for (String key : locationDtoMap.keySet()) {
                Integer numberOfFloor = locationDtoMap.get(key).getNumberOfFloor();
                if (maxNumberOfFloors < numberOfFloor) {
                    maxNumberOfFloors = numberOfFloor;
                }
            }
            areaDto.setNumberOfFloors(maxNumberOfFloors);

            areaDtos.add(areaDto);
        }

//        List<WarehouseArea> areas = entity.getAreas();
//        List<WarehouseAreaRespDto> areaRespDtos = areas.stream().map(warehouseAreaMapper::mapEntityToDto).collect(Collectors.toList());
//        for (WarehouseAreaRespDto areaRespDto : areaRespDtos) {
//            Map<String, WarehouseLocationRespDto> locationRespDtoMap = areaRespDto.getLocations();
//            int maxNumberOfFloors = 0;
//            for (String key : locationRespDtoMap.keySet()) {
//                Integer numberOfFloor = locationRespDtoMap.get(key).getNumberOfFloor();
//                if (maxNumberOfFloors < numberOfFloor) {
//                    maxNumberOfFloors = numberOfFloor;
//                }
//            }
//            areaRespDto.setNumberOfFloors(maxNumberOfFloors);
//        }
        WarehouseStructure warehouseStructure = warehouseStructureRepository.getByWarehouseIdAndCreateDateDesc(id, modifyDate);
        WarehouseRespDto respDto = warehouseMapper.mapEntityToDto(entity);

        respDto.setAreas(areaDtos);
        respDto.setRowNumber(warehouseStructure.getRowNumber());
        respDto.setColumnNumber(warehouseStructure.getColumnNumber());
        return respDto;
    }

    @Override
    public Map<String, WarehouseLocationRespDto> getLocationMapsByWarehouseId(@NonNull Long id, boolean hasFloorDetails) {
        log.info("WarehouseServiceImpl method getLocationMapsByWarehouseId, id: {}", id);
        Warehouse entity = warehouseRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(WarehouseStatus.ID_NOT_FOUND));

        List<WarehouseLocation> warehouseLocations = warehouseLocationRepository.getLocationsByWarehouseCode(entity.getCode());

        if (hasFloorDetails) {
            return warehouseLocations.stream().collect(Collectors.toMap(WarehouseLocation::getMapPoint, e -> warehouseLocationMapper.mapEntityToDto(e, e.getFloors())));
        }

        return warehouseLocations.stream().collect(Collectors.toMap(WarehouseLocation::getMapPoint, warehouseLocationMapper::mapEntityToHiddenDto));
    }

    @Override
    public List<ReportProjectionDto> getReport(@NonNull ReportSearchDto dto) {
        return warehouseRepository.getReport(dto);
    }

    @Override
    public Page<CardRespDto> getCard(@NonNull CardSearchDto dto) {
        List<CardProjectionDto> cards = warehouseRepository.getCard(dto);
        List<CardRespDto> content = cards.stream()
                .map(warehouseMapper::mapProjectionToCardRespDto).collect(Collectors.toList());
        long total = warehouseRepository.count(dto);
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Override
    public Page<ProductFloorDetailProjectionDto> searchProductFloorDetail(@NonNull ProductFloorSearchDto dto) {
        List<ProductFloorDetailProjectionDto> content = productFloorRepository.searchProductFloorDetail(dto);
        long total = productFloorRepository.countProductFloorDetail(dto);
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Override
    public Page<WarehouseRespDto> search(@NonNull WarehouseSearchDto dto) {
        EbsPrincipal principal = EbsSecurityUtils.getCurrentUser();
        List<Warehouse> warehouses = warehouseRepository.search(dto, principal);
        List<WarehouseRespDto> content = warehouses.stream()
                .map(warehouseMapper::mapEntityToDtoHidden).collect(Collectors.toList());
        long total = warehouseRepository.count(dto, principal);
        log.info("WarehouseServiceImpl method search, searchDto: {}", EbsConvertUtils.toString(dto));
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Override
    public WarehouseRespDto add(@NonNull WarehouseReqDto dto) {
        // validate
        dto.setId(null);
        Warehouse entity = createWarehouseEntityFromWarehouseDto(dto);
        entity = warehouseRepository.save(entity);
        dto.setId(entity.getId());
        log.info("WarehouseServiceImpl method add, data: {}", EbsConvertUtils.toString(dto));
        return warehouseMapper.mapEntityToDto(entity);
    }

    @Transactional
    @Override
    public WarehouseRespDto update(@NonNull Long id, @NonNull WarehouseReqDto dto) {
        Warehouse entity = warehouseRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(WarehouseStatus.ID_NOT_FOUND));
        // validate
        dto.setId(id);
        this.deleteProductType(entity, dto);
        this.mapWarehouseDtoToWarehouseEntity(dto, entity);
        this.updateDeletedArea(dto.getDeleteAreaIds());
        this.updateDeletedLocation(dto.getAreas());
        entity = warehouseRepository.save(entity);
        log.info("WarehouseServiceImpl method update, data: {}", EbsConvertUtils.toString(dto));
        return warehouseMapper.mapEntityToDto(entity);
    }

    @Override
    public boolean deleteById(@NonNull Long id) {
        if (!warehouseRepository.existsById(id)) {
            throw new DataRuntimeException(WarehouseStatus.ID_NOT_FOUND);
        }

        try {
            warehouseRepository.deleteById(id);
        } catch (Exception e) {
            log.error("WarehouseServiceImpl method deleteById, id: {}", id, e);
            throw new DataRuntimeException(WarehouseStatus.ID_BEING_USED);
        }
        log.info("WarehouseServiceImpl method deleteById, id: {}", id);
        return true;
    }

    private void mapQuantityToItem(@NonNull ItemDto itemDto) {
        String consignment = Variables.NO_CONSIGNMENT_MESSAGE.equals(itemDto.getConsignmentNumber()) ? Variables.NO_CONSIGNMENT : itemDto.getConsignmentNumber();
        ProductFloorSearchDto searchDto = ProductFloorSearchDto.builder()
                .productId(itemDto.getProductId())
                .unitTargetId(itemDto.getUnitTargetId())
                .productDetailId(itemDto.getProductDetailId())
                .consignment(consignment)
                .build();

        for (ItemDetailDto detailDto : itemDto.getDetails()) {
            Double currentQuantity = this.getQuantityInFloor(searchDto, detailDto);
            detailDto.setQuantity(currentQuantity);
        }
    }

    private Double getQuantityInFloor(@NonNull ProductFloorSearchDto searchDto, @NonNull ItemDetailDto detailDto) {
        searchDto.setFloorId(detailDto.getFloorId());
        searchDto.setInputDate(detailDto.getInputDate());
        return productFloorRepository.getProductQuantity(searchDto);
    }

    private void updateDeletedArea(List<Long> areaIds) {
        if (CollectionUtils.isEmpty(areaIds)) {
            return;
        }
        
        if (productFloorRepository.isAreaUsing(areaIds)) {
            throw new DataRuntimeException(WarehouseStatus.AREA_HAS_USED);
        }
        
        warehouseAreaRepository.updateDeletedByIds(areaIds);
        warehouseLocationRepository.updateDeletedByAreaIds(areaIds);
        warehouseFloorRepository.updateDeletedByAreaIds(areaIds);
    }

    private void updateDeletedLocation(List<WarehouseAreaReqDto> areas) {
        if (CollectionUtils.isEmpty(areas)) {
            return;
        }
        List<Long> locationIds = new ArrayList<>();

        for (WarehouseAreaReqDto area : areas) {
            locationIds.addAll(area.getDeleteLocationIds());
        }

        if (CollectionUtils.isEmpty(locationIds)) {
            return;
        }

        if (productFloorRepository.isLocationUsing(locationIds)) {
            throw new DataRuntimeException(WarehouseStatus.LOCATION_HAS_USED);
        }
        
        warehouseFloorRepository.updateDeletedByLocationIds(locationIds);
        warehouseLocationRepository.updateDeletedByIds(locationIds);
    }

    private void deleteProductType(@NonNull Warehouse entity, @NonNull WarehouseReqDto dto) {
        if (CollectionUtils.isNotEmpty(entity.getProductTypes())) {
            if (CollectionUtils.isEmpty(dto.getProductTypeIds())) {
                warehouseProductTypeConnectRepository.deleteByWarehouseId(entity.getId());
            } else {
                warehouseProductTypeConnectRepository.deleteByWarehouseId(entity.getId(), dto.getProductTypeIds());
            }
        }
    }

    private void mapWarehouseDtoToWarehouseEntity(@NonNull WarehouseReqDto dto, @NonNull Warehouse entity) {
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());

        WarehouseStructure structure = new WarehouseStructure();
        structure.setRowNumber(dto.getRowNumber());
        structure.setColumnNumber(dto.getColumnNumber());
        structure.setWarehouse(entity);
        List<WarehouseStructure> structures = new ArrayList<>();
        structures.add(structure);
        entity.setStructures(structures);

        if (CollectionUtils.isNotEmpty(dto.getProductTypeIds())) {
            List<ProductType> productTypes = productTypeRepository.getByIds(dto.getProductTypeIds());
            if (CollectionUtils.isNotEmpty(entity.getProductTypes())) {
                List<ProductType> productTypeOlds = entity.getProductTypes().stream()
                        .map(WarehouseProductTypeConnect::getProductType).collect(Collectors.toList());
                productTypeOlds.forEach(productTypes::remove);
            }

            List<WarehouseProductTypeConnect> productTypeConnects = productTypes.stream()
                    .map(e -> new WarehouseProductTypeConnect(entity, e)).collect(Collectors.toList());
            entity.setProductTypes(productTypeConnects);
        }

        if (CollectionUtils.isNotEmpty(dto.getAreas())) {
            List<WarehouseArea> areas = new ArrayList<>();
            String maxCode;
            if (CollectionUtils.isEmpty(dto.getDeleteAreaIds())) {
                maxCode = warehouseAreaRepository.getMaxCode(entity.getCode()); // lấy số lượng thực tế trong db
            } else {
                maxCode = warehouseAreaRepository.getMaxCode(entity.getCode(), dto.getDeleteAreaIds()); // lấy số lượng thực tế trong db
            }
            if (maxCode == null) {
                maxCode = "0";
            }
            final int[] index = {(Integer.parseInt(maxCode) + 1)};
            dto.getAreas().forEach(areaDto -> {
                WarehouseArea areaEntity;
                if (areaDto.getId() == null) {
                    areaEntity = this.createAreaEntityFromAreaDto(areaDto, entity, index[0]++);
                } else {
                    areaEntity = warehouseAreaRepository.findById(areaDto.getId())
                            .orElseThrow(() -> new DataRuntimeException(WarehouseStatus.AREA_ID_NOT_FOUND));
                    this.mapAreaDtoToAreaEntity(areaDto, areaEntity);
                }
                areas.add(areaEntity);
            });
            entity.setAreas(areas);
        }
    }

    private void mapAreaDtoToAreaEntity(@NonNull WarehouseAreaReqDto dto, @NonNull WarehouseArea entity) {
        entity.setName(dto.getName());

        if (EbsCollectionsUtils.isNotNullOrEmpty(dto.getAddLocations())) {
            List<WarehouseLocation> locations = new ArrayList<>();
            String maxCode;
            if (CollectionUtils.isEmpty(dto.getDeleteLocationIds())) {
                maxCode = warehouseLocationRepository.getMaxCode(entity.getCode());
            } else {
                maxCode = warehouseLocationRepository.getMaxCode(entity.getCode(), dto.getDeleteLocationIds());
            }
            if (maxCode == null) {
                maxCode = "0";
            }
            final int[] index = {(Integer.parseInt(maxCode) + 1)};
            dto.getAddLocations().forEach((mapPoint, locationDto) -> {
                WarehouseLocation locationEntity = this.createLocationEntityFromLocationDto(locationDto, entity, index[0]++);
                locations.add(locationEntity);
            });
            entity.setLocations(locations);
        }
    }

    private Warehouse createWarehouseEntityFromWarehouseDto(@NonNull WarehouseReqDto dto) {
        Warehouse entity = new Warehouse();
        entity.setName(dto.getName());
        entity.setCode(this.generateWarehouseCode());
        entity.setAddress(dto.getAddress());

        WarehouseStructure structure = new WarehouseStructure();
        structure.setRowNumber(dto.getRowNumber());
        structure.setColumnNumber(dto.getColumnNumber());
        structure.setWarehouse(entity);
        List<WarehouseStructure> structures = new ArrayList<>();
        structures.add(structure);
        entity.setStructures(structures);

        entity.setDeleted(false);

        if (CollectionUtils.isNotEmpty(dto.getProductTypeIds())) {
            List<ProductType> productTypes = productTypeRepository.getByIds(dto.getProductTypeIds());
            List<WarehouseProductTypeConnect> productTypeConnects = productTypes.stream()
                    .map(e -> new WarehouseProductTypeConnect(entity, e)).collect(Collectors.toList());
            entity.setProductTypes(productTypeConnects);
        }

        if (CollectionUtils.isNotEmpty(dto.getAreas())) {
            List<WarehouseArea> areas = new ArrayList<>();
            final int[] index = {0};
            dto.getAreas().forEach(areaDto -> {
                WarehouseArea areaEntity = this.createAreaEntityFromAreaDto(areaDto, entity, index[0]++);
                areas.add(areaEntity);
            });
            entity.setAreas(areas);
        }

        return entity;
    }

    private WarehouseArea createAreaEntityFromAreaDto(@NonNull WarehouseAreaReqDto dto, @NonNull Warehouse warehouseEntity, int numericalOrder) {
        WarehouseArea entity = new WarehouseArea();
        String areaCode = EbsStringUtils.createPath(warehouseEntity.getCode(), String.format(EbsSystem.WH_FORMAT_CODE, EbsSystem.AREA_PREFIX_CODE, numericalOrder));
        entity.setCode(areaCode);
        entity.setWarehouse(warehouseEntity);
        entity.setName(dto.getName());
        entity.setDeleted(false);

        if (EbsCollectionsUtils.isNotNullOrEmpty(dto.getAddLocations())) {
            List<WarehouseLocation> locations = new ArrayList<>();
            final int[] index = {0};
            dto.getAddLocations().forEach((mapPoint, locationDto) -> {
                WarehouseLocation locationEntity = this.createLocationEntityFromLocationDto(locationDto, entity, index[0]++);
                locations.add(locationEntity);
            });
            entity.setLocations(locations);
        }

        return entity;
    }

    private WarehouseLocation createLocationEntityFromLocationDto(@NonNull WarehouseLocationReqDto dto, @NonNull WarehouseArea areaEntity, int numericalOrder) {
        WarehouseLocation entity = new WarehouseLocation();
        entity.setName(areaEntity.getName() + "-" + numericalOrder);
        String code = EbsStringUtils.createPath(areaEntity.getCode(), String.format(EbsSystem.WH_FORMAT_CODE, EbsSystem.LOCATION_PREFIX_CODE, numericalOrder));
        entity.setCode(code);
        entity.setWarehouseArea(areaEntity);
        entity.setDeleted(false);
        entity.setMapPoint(dto.getMapPoint());

        List<WarehouseFloor> floors = new ArrayList<>();
        int numberOfFloors = dto.getNumberOfFloors();
        for (int i = 0; i < numberOfFloors; i++) {
            //lấy số lượng thực tế trong db
            WarehouseFloor floorEntity = this.createFloorEntity(entity, i);
            floors.add(floorEntity);
        }
        entity.setFloors(floors);

        return entity;
    }

    private WarehouseFloor createFloorEntity(@NonNull WarehouseLocation locationEntity, int numericalOrder) {
        WarehouseFloor entity = new WarehouseFloor();
        entity.setName(locationEntity.getName() + "-" + numericalOrder);
        String floorCode = EbsStringUtils.createPath(locationEntity.getCode(), String.format(EbsSystem.WH_FORMAT_CODE, EbsSystem.FLOOR_PREFIX_CODE, numericalOrder));
        entity.setCode(floorCode);
        entity.setDeleted(false);
        entity.setWarehouseLocation(locationEntity);
        return entity;
    }

    public String generateWarehouseCode() {
        String maxCode = warehouseRepository.getMaxCode();
        if (StringUtils.isEmpty(maxCode)) {
            maxCode = "1";
        }
        return String.format(EbsSystem.WH_FORMAT_CODE, EbsSystem.WAREHOUSE_PREFIX_CODE, Integer.parseInt(maxCode) + 1);
    }
}
