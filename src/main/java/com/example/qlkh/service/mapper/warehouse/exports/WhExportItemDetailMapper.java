package com.example.qlkh.service.mapper.warehouse.exports;

import com.example.qlkh.dto.response.warehouse.exports.WhExportItemDetailRespDto;
import com.example.qlkh.entity.warehouse.exports.WhExportItemDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WhExportItemDetailMapper {
    WhExportItemDetailMapper INSTANCE = Mappers.getMapper(WhExportItemDetailMapper.class);

    @Mapping(target = "floor.warehouseLocation", ignore = true)
    @Mapping(target = "mapPoint", source = "floor.warehouseLocation.mapPoint")
    WhExportItemDetailRespDto mapEntityToDto(WhExportItemDetail entity);
}
