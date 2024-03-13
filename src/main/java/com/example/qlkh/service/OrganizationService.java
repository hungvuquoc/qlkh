package com.example.qlkh.service;

import com.example.qlkh.dto.OrganizationDto;

import javax.validation.constraints.NotNull;

public interface OrganizationService {
    OrganizationDto getInfo();

    OrganizationDto update(@NotNull OrganizationDto dto);
}
