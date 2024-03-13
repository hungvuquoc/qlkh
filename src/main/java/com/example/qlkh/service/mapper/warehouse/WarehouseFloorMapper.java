package com.example.qlkh.service.mapper.warehouse;

import com.example.qlkh.dto.response.warehouse.WarehouseFloorRespDto;
import com.example.qlkh.entity.warehouse.WarehouseFloor;
import com.example.qlkh.entity.warehouse.WarehouseLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WarehouseFloorMapper {
    WarehouseFloorMapper INSTANCE = Mappers.getMapper(WarehouseFloorMapper.class);

    @Mapping(target = "tag", expression = "java(mapTag(entity))")
    @Mapping(target = "warehouseLocation", ignore = true)
    WarehouseFloorRespDto mapEntityToDto(WarehouseFloor entity);

    default String mapTag(WarehouseFloor floor) {
        if (floor == null) {
            return null;
        }
        WarehouseLocation location = floor.getWarehouseLocation();
        if (location == null) {
            return floor.getName();
        }
        StringBuilder tag = new StringBuilder();
        tag.append(location.getName());
        tag.append(" - ");
        tag.append(floor.getName());
        return tag.toString();
    }
}
