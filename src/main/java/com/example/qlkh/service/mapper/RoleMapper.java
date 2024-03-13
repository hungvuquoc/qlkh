package com.example.qlkh.service.mapper;

import com.example.qlkh.dto.response.RoleRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseRespDto;
import com.example.qlkh.entity.Authority;
import com.example.qlkh.entity.Role;
import com.example.qlkh.entity.RoleAuthorityConnect;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.service.mapper.warehouse.WarehouseMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "description", source = "entity.description")
    @Mapping(target = "warehouse", expression = "java(mapWarehouse(warehouse))")
    @Mapping(target = "authorities", expression = "java(mapAuthorities(authorityConnects))")
    RoleRespDto entityToDto(Role entity, Warehouse warehouse, List<RoleAuthorityConnect> authorityConnects);

    @Mapping(target = "authorities", ignore = true)
    RoleRespDto entityToDto(Role entity);

    default WarehouseRespDto mapWarehouse(Warehouse warehouse) {
        return WarehouseMapper.INSTANCE.mapEntityToDto(warehouse, null, null);
    }

    default List<String> mapAuthorities(List<RoleAuthorityConnect> authorityConnects) {
        if (CollectionUtils.isEmpty(authorityConnects)) {
            return Collections.emptyList();
        }
        return authorityConnects.stream().map(e -> e.getAuthority().getName()).collect(Collectors.toList());
    }
}
