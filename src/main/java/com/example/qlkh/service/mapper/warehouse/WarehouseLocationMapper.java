package com.example.qlkh.service.mapper.warehouse;

import com.example.qlkh.dto.response.warehouse.WarehouseFloorRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseLocationRespDto;
import com.example.qlkh.entity.warehouse.WarehouseFloor;
import com.example.qlkh.entity.warehouse.WarehouseLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WarehouseLocationMapper {
    WarehouseLocationMapper INSTANCE = Mappers.getMapper(WarehouseLocationMapper.class);

    @Mapping(target = "warehouseArea", ignore = true)
    @Mapping(target = "floors", source = "floors", qualifiedByName = "mapListFloorEntityToListFloorDto")
    WarehouseLocationRespDto mapEntityToDto(WarehouseLocation entity, List<WarehouseFloor> floors);

    @Mapping(target = "warehouseArea", ignore = true)
    @Mapping(target = "floors", qualifiedByName = "mapListFloorEntityToListFloorDto")
    @Mapping(target = "numberOfFloor", expression = "java(entity.getFloors() != null ? entity.getFloors().size() : 0)")
    WarehouseLocationRespDto mapEntityToHiddenDto(WarehouseLocation entity);

    @Named("mapListFloorEntityToListFloorDto")
    default List<WarehouseFloorRespDto> mapListFloorEntityToListFloorDto(List<WarehouseFloor> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        }
        return entities.stream().map(WarehouseFloorMapper.INSTANCE::mapEntityToDto).collect(Collectors.toList());
    }
}
