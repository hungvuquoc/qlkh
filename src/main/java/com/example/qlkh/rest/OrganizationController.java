package com.example.qlkh.rest;

import com.example.qlkh.dto.OrganizationDto;
import com.example.qlkh.entity.Organization;
import com.example.qlkh.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info")
    public OrganizationDto getInfo() {
        return organizationService.getInfo();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/info")
    public OrganizationDto update(@RequestBody OrganizationDto dto) {
        return organizationService.update(dto);
    }
}
