package com.example.qlkh.service;

import com.example.qlkh.dto.request.EmployeeReqDto;
import com.example.qlkh.dto.request.searchs.EmployeeSearchDto;
import com.example.qlkh.dto.response.EmployeeRespDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    EmployeeRespDto getById(@NonNull Long id);
    Page<EmployeeRespDto> search(@NonNull EmployeeSearchDto dto);
    EmployeeRespDto create(@NonNull EmployeeReqDto dto);
    EmployeeRespDto update(@NonNull Long id, @NonNull EmployeeReqDto dto);
    Boolean deleteById(@NonNull Long id);
}
