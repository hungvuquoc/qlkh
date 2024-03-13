package com.example.qlkh.service.mapper.warehouse.exports;

import com.example.qlkh.constant.Variables;
import com.example.qlkh.dto.response.products.ProductRespDto;
import com.example.qlkh.dto.response.products.ProductUnitRespDto;
import com.example.qlkh.dto.response.warehouse.exports.WhExportItemDetailRespDto;
import com.example.qlkh.dto.response.warehouse.exports.WhExportItemRespDto;
import com.example.qlkh.entity.product.Product;
import com.example.qlkh.entity.product.ProductUnitConnect;
import com.example.qlkh.entity.warehouse.exports.WhExportItem;
import com.example.qlkh.entity.warehouse.exports.WhExportItemDetail;
import com.example.qlkh.service.mapper.products.ProductMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WhExportItemMapper {
    WhExportItemMapper INSTANCE = Mappers.getMapper(WhExportItemMapper.class);

    @Mapping(target = "consignmentNumber", expression = "java(mapConsignment(entity.getConsignmentNumber()))")
    @Mapping(target = "product", expression = "java(mapProduct(entity.getProduct()))")
    @Mapping(target = "unitTarget", expression = "java(mapUnit(entity.getUnitTarget()))")
    @Mapping(target = "unitSource", expression = "java(mapUnit(entity.getUnitSource()))")
    @Mapping(target = "details", qualifiedByName = "mapDetailEntitiesToDtos")
    WhExportItemRespDto mapEntityToDto(WhExportItem entity);

    @Named("mapDetailEntitiesToDtos")
    default List<WhExportItemDetailRespDto> mapDetailEntitiesToDtos(List<WhExportItemDetail> details) {
        if (CollectionUtils.isEmpty(details)) {
            return Collections.emptyList();
        }

        return details.stream().map(WhExportItemDetailMapper.INSTANCE::mapEntityToDto)
                .collect(Collectors.toList());
    }

    default ProductUnitRespDto mapUnit(ProductUnitConnect unitConnect) {
        return ProductMapper.INSTANCE.mapUnitEntityToUnitDto(unitConnect);
    }

    default ProductRespDto mapProduct(Product product) {
        return ProductMapper.INSTANCE.mapEntityToDtoHidden(product);
    }

    default String mapConsignment(String consignment) {
        if (Variables.NO_CONSIGNMENT.equals(consignment)) {
            return Variables.NO_CONSIGNMENT_MESSAGE;
        }
        return consignment;
    }
}
