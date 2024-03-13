package com.example.qlkh.service.mapper.warehouse;

import com.example.qlkh.dto.EbsWarehouse;
import com.example.qlkh.dto.EbsWarehouseProjection;
import com.example.qlkh.dto.response.products.ProductTypeRespDto;
import com.example.qlkh.dto.response.projection.CardProjectionDto;
import com.example.qlkh.dto.response.warehouse.CardRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseAreaRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseRespDto;
import com.example.qlkh.entity.product.ProductType;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.entity.warehouse.WarehouseArea;
import com.example.qlkh.entity.warehouse.WarehouseProductTypeConnect;
import com.example.qlkh.service.mapper.products.ProductTypeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    @Mapping(target = "areas", qualifiedByName = "mapListAreaEntityToListAreaDto")
    @Mapping(target = "productTypes", source = "productTypes", qualifiedByName = "mapListProductTypeToListProductTypeDto")
    WarehouseRespDto mapEntityToDto(Warehouse entity);

    @Mapping(target = "areas", source = "areas", qualifiedByName = "mapListAreaEntityToListAreaDto")
    @Mapping(target = "productTypes", qualifiedByName = "mapListProductTypeToListProductTypeDto")
    WarehouseRespDto mapEntityToDto(Warehouse entity, List<WarehouseArea> areas);

    @Mapping(target = "areas", expression = "java(mapListAreaEntityToListAreaDto(areas))")
    @Mapping(target = "productTypes", expression = "java(mapListProductTypeToListProductTypeDto(productTypeConnects))")
    WarehouseRespDto mapEntityToDto(Warehouse entity, List<WarehouseArea> areas,List<WarehouseProductTypeConnect> productTypeConnects);

    @Mapping(target = "areas", ignore = true)
    @Mapping(target = "productTypes", source = "productTypes", qualifiedByName = "mapListProductTypeToListProductTypeDto")
    WarehouseRespDto mapEntityToDtoHidden(Warehouse entity);

    EbsWarehouse mapProjectionToEbsWarehouse(EbsWarehouseProjection projection);

    CardRespDto mapProjectionToCardRespDto(CardProjectionDto projection);

    @Named("mapListAreaEntityToListAreaDto")
    default List<WarehouseAreaRespDto> mapListAreaEntityToListAreaDto(List<WarehouseArea> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }

        return entities.stream().map(WarehouseAreaMapper.INSTANCE::mapEntityToDto).collect(Collectors.toList());
    }

    @Named("mapListProductTypeToListProductTypeDto")
    default List<ProductTypeRespDto> mapListProductTypeToListProductTypeDto(List<WarehouseProductTypeConnect> productTypeConnects) {
        if (CollectionUtils.isEmpty(productTypeConnects)) {
            return Collections.emptyList();
        }

        return productTypeConnects.stream()
                .map(pt -> ProductTypeMapper.INSTANCE.mapEntityToDto(pt.getProductType()))
                .collect(Collectors.toList());
    }
}
