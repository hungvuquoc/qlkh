package com.example.qlkh.service.mapper.products;

import com.example.qlkh.dto.response.products.ProductGroupRespDto;
import com.example.qlkh.entity.product.ProductGroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductGroupMapper {
    ProductGroupMapper INSTANCE = Mappers.getMapper(ProductGroupMapper.class);
    ProductGroupRespDto mapEntityToDto(ProductGroup entity);
}
