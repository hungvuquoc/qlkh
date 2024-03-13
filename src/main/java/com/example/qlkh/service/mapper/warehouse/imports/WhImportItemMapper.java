package com.example.qlkh.service.mapper.warehouse.imports;

import com.example.qlkh.dto.response.products.ProductRespDto;
import com.example.qlkh.dto.response.warehouse.imports.WhImportItemDetailRespDto;
import com.example.qlkh.dto.response.warehouse.imports.WhImportItemRespDto;
import com.example.qlkh.entity.product.Product;
import com.example.qlkh.entity.warehouse.imports.WhImportItem;
import com.example.qlkh.entity.warehouse.imports.WhImportItemDetail;
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
public interface WhImportItemMapper {
    WhImportItemMapper INSTANCE = Mappers.getMapper(WhImportItemMapper.class);

    @Mapping(target = "product", qualifiedByName = "mapProductEntityToDto")
    @Mapping(target = "details", qualifiedByName = "mapDetailEntitiesToDtos")
    WhImportItemRespDto mapEntityToDto(WhImportItem entity);

    @Named("mapDetailEntitiesToDtos")
    default List<WhImportItemDetailRespDto> mapDetailEntitiesToDtos(List<WhImportItemDetail> details) {
        if (CollectionUtils.isEmpty(details)) {
            return Collections.emptyList();
        }

        return details.stream().map(WhImportItemDetailMapper.INSTANCE::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Named("mapProductEntityToDto")
    default ProductRespDto mapProductEntityToDto(Product product) {
        if (product == null) {
            return null;
        }

        return ProductMapper.INSTANCE.mapEntityToDto(product, product.getUnits(), product.getDetails());
    }
}
