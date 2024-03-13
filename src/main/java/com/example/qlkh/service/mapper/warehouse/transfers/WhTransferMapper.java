package com.example.qlkh.service.mapper.warehouse.transfers;

import com.example.qlkh.dto.response.warehouse.WarehouseRespDto;
import com.example.qlkh.dto.response.warehouse.transfers.WhTransferItemRespDto;
import com.example.qlkh.dto.response.warehouse.transfers.WhTransferRespDto;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.entity.warehouse.transfers.WhTransfer;
import com.example.qlkh.entity.warehouse.transfers.WhTransferItem;
import com.example.qlkh.service.mapper.warehouse.WarehouseMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WhTransferMapper {
    WhTransferMapper INSTANCE = Mappers.getMapper(WhTransferMapper.class);

    @Mapping(target = "warehouseImport", expression = "java(mapWarehouse(entity.getWarehouseImport()))")
    @Mapping(target = "warehouseExport", expression = "java(mapWarehouse(entity.getWarehouseExport()))")
    @Mapping(target = "items", ignore = true)
    WhTransferRespDto mapEntityToDto(WhTransfer entity);

    @Mapping(target = "warehouseImport", expression = "java(mapWarehouse(entity.getWarehouseImport()))")
    @Mapping(target = "warehouseExport", expression = "java(mapWarehouse(entity.getWarehouseExport()))")
    @Mapping(target = "items", expression = "java(mapItems(items))")
    WhTransferRespDto mapEntityToDto(WhTransfer entity, List<WhTransferItem> items);

    default List<WhTransferItemRespDto> mapItems(List<WhTransferItem> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items.stream().map(WhTransferItemMapper.INSTANCE::mapEntityToDto).collect(Collectors.toList());
    }

    default WarehouseRespDto mapWarehouse(Warehouse warehouse) {
        return WarehouseMapper.INSTANCE.mapEntityToDto(warehouse, null, null);
    }
}
