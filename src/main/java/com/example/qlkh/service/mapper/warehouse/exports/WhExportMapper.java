package com.example.qlkh.service.mapper.warehouse.exports;

import com.example.qlkh.dto.response.warehouse.WarehouseRespDto;
import com.example.qlkh.dto.response.warehouse.exports.WhExportItemRespDto;
import com.example.qlkh.dto.response.warehouse.exports.WhExportRespDto;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.entity.warehouse.exports.WhExport;
import com.example.qlkh.entity.warehouse.exports.WhExportItem;
import com.example.qlkh.service.mapper.warehouse.WarehouseMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WhExportMapper {
    @Mapping(target = "code", source = "code")
    @Mapping(target = "warehouse", expression = "java(mapWarehouse(entity.getWarehouse()))")
    @Mapping(target = "items", qualifiedByName = "mapItemEntityToDto")
    WhExportRespDto mapEntityToDto(WhExport entity);

    @Mapping(target = "code", source = "entity.code")
    @Mapping(target = "warehouse", expression = "java(mapWarehouse(entity.getWarehouse()))")
    @Mapping(target = "items", source = "items", qualifiedByName = "mapItemEntityToDto")
    WhExportRespDto mapEntityToDto(WhExport entity, List<WhExportItem> items);

    @Named("mapItemEntityToDto")
    default List<WhExportItemRespDto> mapItemEntityToDto(List<WhExportItem> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream().map(WhExportItemMapper.INSTANCE::mapEntityToDto)
                .collect(Collectors.toList());
    }

    default WarehouseRespDto mapWarehouse(Warehouse warehouse) {
        return WarehouseMapper.INSTANCE.mapEntityToDtoHidden(warehouse);
    }
}
