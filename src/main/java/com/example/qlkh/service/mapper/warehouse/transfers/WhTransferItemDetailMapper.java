package com.example.qlkh.service.mapper.warehouse.transfers;

import com.example.qlkh.dto.response.warehouse.transfers.WhTransferItemDetailRespDto;
import com.example.qlkh.entity.warehouse.transfers.WhTransferItemDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WhTransferItemDetailMapper {
    WhTransferItemDetailMapper INSTANCE = Mappers.getMapper(WhTransferItemDetailMapper.class);

    @Mapping(target = "floor.warehouseLocation", ignore = true)
    @Mapping(target = "mapPoint", source = "floor.warehouseLocation.mapPoint")
    WhTransferItemDetailRespDto mapEntityToDto(WhTransferItemDetail entity);
}
