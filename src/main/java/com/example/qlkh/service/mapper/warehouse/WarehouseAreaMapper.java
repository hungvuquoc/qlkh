package com.example.qlkh.service.mapper.warehouse;

import com.example.qlkh.dto.response.warehouse.WarehouseAreaRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseLocationRespDto;
import com.example.qlkh.entity.warehouse.WarehouseArea;
import com.example.qlkh.entity.warehouse.WarehouseLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WarehouseAreaMapper {
    WarehouseAreaMapper INSTANCE = Mappers.getMapper(WarehouseAreaMapper.class);

    @Mapping(target = "warehouse", ignore = true)
    @Mapping(target = "locations", qualifiedByName = "mapListLocationEntityToListLocationDto")
    WarehouseAreaRespDto mapEntityToDto(WarehouseArea entity);

    @Mapping(target = "warehouse", ignore = true)
    @Mapping(target = "locations", source = "locations", qualifiedByName = "mapListLocationEntityToListLocationDto")
    WarehouseAreaRespDto mapEntityToDto(WarehouseArea entity, List<WarehouseLocation> locations);

    @Named("mapListLocationEntityToListLocationDto")
    default Map<String, WarehouseLocationRespDto> mapListLocationEntityToListLocationDto(List<WarehouseLocation> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        }

        Map<String, WarehouseLocationRespDto> respDtos = new HashMap<>();
        for (WarehouseLocation location : entities) {
            if (respDtos.containsKey(location.getMapPoint())) {
                continue;
            }
            WarehouseLocationRespDto dto = WarehouseLocationMapper.INSTANCE.mapEntityToHiddenDto(location);
//            if (dto.getDeleted()) {
//                continue;
//            }
            respDtos.put(dto.getMapPoint(), dto);
        }

        return respDtos;
    }
}
