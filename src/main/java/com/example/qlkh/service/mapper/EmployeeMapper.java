package com.example.qlkh.service.mapper;

import com.example.qlkh.dto.response.EmployeeRespDto;
import com.example.qlkh.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeRespDto mapEntityToDto(Employee entity);
}
