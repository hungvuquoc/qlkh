package com.example.qlkh.service.mapper;

import com.example.qlkh.dto.OrganizationDto;
import com.example.qlkh.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    OrganizationDto entityToDto(Organization entity);
    Organization dtoToEntity(OrganizationDto dto);
}
