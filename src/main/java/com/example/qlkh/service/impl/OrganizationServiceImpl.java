package com.example.qlkh.service.impl;

import com.example.qlkh.constant.Variables;
import com.example.qlkh.dto.OrganizationDto;
import com.example.qlkh.entity.Organization;
import com.example.qlkh.error.status.CommonStatus;
import com.example.qlkh.error.status.OrganizationStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.repository.OrganizationRepository;
import com.example.qlkh.service.OrganizationService;
import com.example.qlkh.service.mapper.OrganizationMapper;
import com.example.qlkh.utils.EbsSecurityUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    @Override
    public OrganizationDto getInfo() {
        Organization entity = organizationRepository.getInfo();
        return organizationMapper.entityToDto(entity);
    }

    @Override
    public OrganizationDto update(OrganizationDto dto) {
        boolean permission = EbsSecurityUtils.hasRootRole();
        if (!permission) {
            throw new DataRuntimeException(CommonStatus.FORBIDDEN);
        }
        this.validate(dto);
        Organization entity = organizationRepository.getInfo();
        this.mapDtoToEntity(dto, entity);
        entity = organizationRepository.save(entity);
        return organizationMapper.entityToDto(entity);
    }

    private void validate(@NonNull OrganizationDto dto) {
        if (StringUtils.isEmpty(dto.getName())) {
            throw new DataRuntimeException(OrganizationStatus.NAME_IS_EMPTY);
        }

        if (StringUtils.isEmpty(dto.getEnterpriseCode())) {
            throw new DataRuntimeException(OrganizationStatus.ENTERPRISE_CODE_IS_EMPTY);
        }

        if (dto.getEnterpriseCode().length() != Variables.ENTERPRISE_CODE_LENGTH) {
            throw new DataRuntimeException(OrganizationStatus.ENTERPRISE_WRONG_FORMAT);
        }

        if (StringUtils.isEmpty(dto.getManagerName())) {
            throw new DataRuntimeException(OrganizationStatus.MANAGER_NAME_IS_EMPTY);
        }

        if (StringUtils.isEmpty(dto.getAddress())) {
            throw new DataRuntimeException(OrganizationStatus.ADDRESS_IS_EMPTY);
        }

        if (StringUtils.isEmpty(dto.getPhone())) {
            throw new DataRuntimeException(OrganizationStatus.PHONE_IS_EMPTY);
        }

        if (!dto.getPhone().matches(Variables.REGEX_PHONE)) {
            throw new DataRuntimeException(OrganizationStatus.PHONE_WRONG_FORMAT);
        }

        if (StringUtils.isEmpty(dto.getEmail())) {
            throw new DataRuntimeException(OrganizationStatus.EMAIL_IS_EMPTY);
        }

        if (!dto.getEmail().matches(Variables.REGEX_EMAIL)) {
            throw new DataRuntimeException(OrganizationStatus.EMAIL_WRONG_FORMAT);
        }
    }

    private void mapDtoToEntity(@NonNull OrganizationDto dto, @NonNull Organization entity) {
        entity.setName(dto.getName());
        entity.setEnterpriseCode(dto.getEnterpriseCode());
        entity.setManagerName(dto.getManagerName());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setDescription(dto.getDescription());
    }
}
