package com.example.qlkh.service.mapper;

import com.example.qlkh.dto.FileDescriptionDto;
import com.example.qlkh.entity.FileDescription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FileDescriptionMapper {
    FileDescriptionMapper INSTANCE = Mappers.getMapper(FileDescriptionMapper.class);
    FileDescriptionDto entityToDto(FileDescription entity);
}
