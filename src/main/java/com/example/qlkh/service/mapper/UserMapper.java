package com.example.qlkh.service.mapper;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.response.EmployeeRespDto;
import com.example.qlkh.dto.response.UserRespDto;
import com.example.qlkh.entity.*;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "roles", expression = "java(mapRoles(roleConnects))")
    @Mapping(target = "authorities", expression = "java(mapAuthorityEntities(authorities))")
    UserRespDto entityToDto(User user, List<UserRoleConnect> roleConnects, List<UserAuthorityConnect> authorities);

    @Mapping(target = "roles", expression = "java(mapRoles(entity.getRoles()))")
    @Mapping(target = "authorities", expression = "java(mapAuthorityEntities(entity.getAuthorityEntities()))")
    UserRespDto entityToDto(User entity);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "createDate", source = "entity.createDate")
    @Mapping(target = "createBy", source = "entity.createBy")
    @Mapping(target = "modifyDate", source = "entity.modifyDate")
    @Mapping(target = "modifyBy", source = "entity.modifyBy")
    @Mapping(target = "roles", expression = "java(mapRoles(entity.getRoles()))")
    @Mapping(target = "employee", expression = "java(mapEmployee(employee))")
    @Mapping(target = "authorities", expression = "java(mapAuthorityEntities(entity.getAuthorityEntities()))")
    UserRespDto entityToDto(User entity, Employee employee);

    @Mapping(target = "roles", expression = "java(mapRoles(entity.getRoles()))")
    @Mapping(target = "authorities", expression = "java(mapAuthorities(entity.getAuthorities()))")
    EbsPrincipal entityToPrincipal(User entity);

    default Set<String> mapRoles(List<UserRoleConnect> roleConnects) {
        if (CollectionUtils.isEmpty(roleConnects)) {
            return Collections.emptySet();
        }

        Set<String> response = new HashSet<>();
        for (UserRoleConnect roleConnect : roleConnects) {
            response.add(roleConnect.getRole().getName());
        }
        return response;
    }

    default Set<String> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        if (CollectionUtils.isEmpty(authorities)) {
            return Collections.emptySet();
        }
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }

    default Set<String> mapAuthorityEntities(List<UserAuthorityConnect> authorityConnects) {
        if (CollectionUtils.isEmpty(authorityConnects)) {
            return Collections.emptySet();
        }
        List<Authority> authorities = authorityConnects.stream()
                .map(UserAuthorityConnect::getAuthority).collect(Collectors.toList());
        return mapAuthorities(authorities);
    }

    default EmployeeRespDto mapEmployee(Employee employee) {
        if (employee == null) {
            return null;
        }
        return EmployeeMapper.INSTANCE.mapEntityToDto(employee);
    }
}
