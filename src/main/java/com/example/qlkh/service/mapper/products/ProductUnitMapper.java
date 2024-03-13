package com.example.qlkh.service.mapper.products;

import com.example.qlkh.dto.response.products.ProductUnitRespDto;
import com.example.qlkh.entity.product.ProductUnit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductUnitMapper {
    ProductUnitMapper INSTANCE = Mappers.getMapper(ProductUnitMapper.class);
    ProductUnitRespDto mapEntityToDto(ProductUnit entity);
}
