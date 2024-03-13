package com.example.qlkh.service.mapper.warehouse.transfers;

import com.example.qlkh.constant.Variables;
import com.example.qlkh.dto.response.products.ProductRespDto;
import com.example.qlkh.dto.response.products.ProductUnitRespDto;
import com.example.qlkh.dto.response.warehouse.transfers.WhTransferItemDetailRespDto;
import com.example.qlkh.dto.response.warehouse.transfers.WhTransferItemRespDto;
import com.example.qlkh.entity.product.Product;
import com.example.qlkh.entity.product.ProductUnitConnect;
import com.example.qlkh.entity.warehouse.transfers.WhTransferItem;
import com.example.qlkh.entity.warehouse.transfers.WhTransferItemDetail;
import com.example.qlkh.service.mapper.products.ProductMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WhTransferItemMapper {
    WhTransferItemMapper INSTANCE = Mappers.getMapper(WhTransferItemMapper.class);

    @Mapping(target = "consignmentNumber", expression = "java(mapConsignment(entity.getConsignmentNumber()))")
    @Mapping(target = "product", expression = "java(mapProduct(entity.getProduct()))")
    @Mapping(target = "unitTarget", expression = "java(mapUnit(entity.getUnitTarget()))")
    @Mapping(target = "unitSource", expression = "java(mapUnit(entity.getUnitSource()))")
    @Mapping(target = "details", expression = "java(mapDetails(entity.getDetails()))")
    WhTransferItemRespDto mapEntityToDto(WhTransferItem entity);

    default List<WhTransferItemDetailRespDto> mapDetails(List<WhTransferItemDetail> details) {
        if (CollectionUtils.isEmpty(details)) {
            return Collections.emptyList();
        }

        return details.stream().map(WhTransferItemDetailMapper.INSTANCE::mapEntityToDto).collect(Collectors.toList());
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
