package com.example.qlkh.service.mapper;

import com.example.qlkh.dto.response.AuthorityRespDto;
import com.example.qlkh.entity.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {
    AuthorityMapper INSTANCE = Mappers.getMapper(AuthorityMapper.class);

    AuthorityRespDto entityToDto(Authority entity);
}
