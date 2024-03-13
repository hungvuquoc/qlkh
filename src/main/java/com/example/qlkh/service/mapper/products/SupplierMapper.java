package com.example.qlkh.service.mapper.products;

import com.example.qlkh.dto.response.products.SupplierRespDto;
import com.example.qlkh.entity.product.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);
    SupplierRespDto mapEntityToDto(Supplier entity);
}
