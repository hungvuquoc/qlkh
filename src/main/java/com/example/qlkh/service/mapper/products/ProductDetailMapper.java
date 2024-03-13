package com.example.qlkh.service.mapper.products;

import com.example.qlkh.dto.response.products.ProductDetailRespDto;
import com.example.qlkh.entity.product.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductDetailMapper {
    ProductDetailMapper INSTANCE = Mappers.getMapper(ProductDetailMapper.class);
    ProductDetailRespDto mapEntityToDto(ProductDetail entity);
}
