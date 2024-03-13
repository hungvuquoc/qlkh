package com.example.qlkh.service.impl;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.request.EmployeeReqDto;
import com.example.qlkh.dto.request.searchs.EmployeeSearchDto;
import com.example.qlkh.dto.response.EmployeeRespDto;
import com.example.qlkh.entity.Employee;
import com.example.qlkh.error.status.EmployeeStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.repository.EmployeeRepository;
import com.example.qlkh.service.EmployeeService;
import com.example.qlkh.service.mapper.EmployeeMapper;
import com.example.qlkh.utils.EbsConvertUtils;
import com.example.qlkh.utils.EbsSecurityUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeRespDto getById(@NonNull Long id) {
        log.info("EmployeeServiceImpl method getById, id: {}", id);
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(EmployeeStatus.ID_NOT_FOUND));
        return employeeMapper.mapEntityToDto(entity);
    }

    @Override
    public Page<EmployeeRespDto> search(EmployeeSearchDto dto) {
        log.info("EmployeeServiceImpl method getById, condition: {}", EbsConvertUtils.toString(dto));
        EbsPrincipal principal = EbsSecurityUtils.getCurrentUser();
        List<Employee> entities = employeeRepository.search(dto, principal);
        List<EmployeeRespDto> content = entities.stream().map(employeeMapper::mapEntityToDto).collect(Collectors.toList());
        long total = employeeRepository.count(dto, principal);
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Override
    public EmployeeRespDto create(@NonNull EmployeeReqDto dto) {
        dto.setId(null);
        this.validateDto(dto);
        Employee entity = new Employee();
        this.mapDtoToEntity(dto, entity);
        entity = employeeRepository.save(entity);
        log.info("EmployeeServiceImpl method create, data: {}", EbsConvertUtils.toString(entity));
        return employeeMapper.mapEntityToDto(entity);
    }

    @Override
    public EmployeeRespDto update(@NonNull Long id, @NonNull EmployeeReqDto dto) {
        dto.setId(id);
        this.validateDto(dto);
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(EmployeeStatus.ID_NOT_FOUND));
        log.info("EmployeeServiceImpl method update [old], id: {}, data: {}", id, EbsConvertUtils.toString(entity));
        this.mapDtoToEntity(dto, entity);
        this.updateUser(entity);
        entity = employeeRepository.save(entity);
        log.info("EmployeeServiceImpl method update [new], id: {}, data: {}", id, EbsConvertUtils.toString(entity));
        return employeeMapper.mapEntityToDto(entity);
    }

    @Override
    public Boolean deleteById(@NonNull Long id) {
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(EmployeeStatus.ID_NOT_FOUND));

        if (employeeRepository.hasUsed(id)) {
            entity.setDeleted(true);
            employeeRepository.save(entity);
            log.info("EmployeeServiceImpl method deleteById [hidden], id: {}, data: {}", id, EbsConvertUtils.toString(entity));
            return true;
        }
        employeeRepository.deleteById(id);
        log.info("EmployeeServiceImpl method deleteById [delete], id: {}, data: {}", id, EbsConvertUtils.toString(entity));
        return true;
    }

    private void updateUser(@NonNull Employee employee) {
        if (employee.getUser() != null) {
            employee.getUser().setActive(!employee.isDeleted());
        }
    }

    private void validateDto(@NonNull EmployeeReqDto dto) {
//        if (dto.getId() != null && employeeRepository.hasAccount(dto.getId())) {
//            throw new DataRuntimeException(EmployeeStatus.HAS_ACCOUNT);
//        }
    }

    private void mapDtoToEntity(@NonNull EmployeeReqDto dto, @NonNull Employee entity) {
        if (dto.getId() == null) {
            entity.setCode(this.generateCode());
        }
        entity.setName(dto.getName());
        entity.setBirthday(dto.getBirthday());
        entity.setPhone(dto.getPhone());
        entity.setGender(dto.getGender());
        entity.setDeleted(dto.isDeleted());
    }

    private String generateCode() {
        String maxCode = employeeRepository.getMaxCode();
        if (StringUtils.isEmpty(maxCode)) {
            maxCode = "0";
        }
        return String.format("%05d", Integer.parseInt(maxCode) + 1);
    }
}
