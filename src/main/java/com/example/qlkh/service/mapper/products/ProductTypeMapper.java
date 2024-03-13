package com.example.qlkh.service.mapper.products;

import com.example.qlkh.dto.response.products.ProductTypeRespDto;
import com.example.qlkh.entity.product.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper {
    ProductTypeMapper INSTANCE = Mappers.getMapper(ProductTypeMapper.class);

    ProductTypeRespDto mapEntityToDto(ProductType entity);
}
