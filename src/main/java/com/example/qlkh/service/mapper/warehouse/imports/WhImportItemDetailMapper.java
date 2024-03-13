package com.example.qlkh.service.mapper.warehouse.imports;

import com.example.qlkh.dto.response.warehouse.WarehouseLocationRespDto;
import com.example.qlkh.dto.response.warehouse.imports.WhImportItemDetailRespDto;
import com.example.qlkh.entity.warehouse.WarehouseLocation;
import com.example.qlkh.entity.warehouse.imports.WhImportItemDetail;
import com.example.qlkh.service.mapper.warehouse.WarehouseLocationMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WhImportItemDetailMapper {
    WhImportItemDetailMapper INSTANCE = Mappers.getMapper(WhImportItemDetailMapper.class);

    @Mapping(target = "floor.warehouseLocation", ignore = true)
    @Mapping(target = "mapPoint", source = "floor.warehouseLocation.mapPoint")
    @Mapping(target = "location", source = "floor.warehouseLocation", qualifiedByName = "mapLocationEntityToDto")
    WhImportItemDetailRespDto mapEntityToDto(WhImportItemDetail entity);

    @Named("mapLocationEntityToDto")
    default WarehouseLocationRespDto mapLocationEntityToDto(WarehouseLocation location) {
        if (location == null) {
            return null;
        }
        return WarehouseLocationMapper.INSTANCE.mapEntityToHiddenDto(location);
    }
}
