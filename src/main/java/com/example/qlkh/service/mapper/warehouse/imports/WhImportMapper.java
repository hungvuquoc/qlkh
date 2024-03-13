package com.example.qlkh.service.mapper.warehouse.imports;

import com.example.qlkh.dto.response.warehouse.WarehouseRespDto;
import com.example.qlkh.dto.response.warehouse.imports.WhImportItemRespDto;
import com.example.qlkh.dto.response.warehouse.imports.WhImportRespDto;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.entity.warehouse.imports.WhImport;
import com.example.qlkh.entity.warehouse.imports.WhImportItem;
import com.example.qlkh.service.mapper.warehouse.WarehouseMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WhImportMapper {
    WhImportMapper INSTANCE = Mappers.getMapper(WhImportMapper.class);

    @Mapping(target = "warehouseName", source = "warehouse.name")
    @Mapping(target = "warehouse", source = "warehouse", qualifiedByName = "mapWarehouseEntityToDto")
    @Mapping(target = "items", qualifiedByName = "mapItemEntityToDto")
    WhImportRespDto mapEntityToDto(WhImport entity);

    @Mapping(target = "warehouse.areas", ignore = true)
    @Mapping(target = "items", source = "items", qualifiedByName = "mapItemEntityToDto")
    WhImportRespDto mapEntityToDto(WhImport entity, List<WhImportItem> items);

    @Named("mapItemEntityToDto")
    default List<WhImportItemRespDto> mapItemEntityToDto(List<WhImportItem> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream().map(WhImportItemMapper.INSTANCE::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Named("mapWarehouseEntityToDto")
    default WarehouseRespDto mapWarehouseEntityToDto(Warehouse warehouse) {
        return WarehouseMapper.INSTANCE.mapEntityToDto(warehouse, null);
    }
}
