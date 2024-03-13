package com.example.qlkh.service.impl;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.request.RoleReqDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.dto.response.AuthorityRespDto;
import com.example.qlkh.dto.response.RoleRespDto;
import com.example.qlkh.entity.Authority;
import com.example.qlkh.entity.Role;
import com.example.qlkh.entity.RoleAuthorityConnect;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.error.status.RoleStatus;
import com.example.qlkh.exception.DataRuntimeException;
import com.example.qlkh.repository.AuthorityRepository;
import com.example.qlkh.repository.RoleAuthorityConnectRepository;
import com.example.qlkh.repository.RoleRepository;
import com.example.qlkh.repository.UserRoleConnectRepository;
import com.example.qlkh.repository.warehouse.WarehouseRepository;
import com.example.qlkh.service.RoleService;
import com.example.qlkh.service.mapper.AuthorityMapper;
import com.example.qlkh.service.mapper.RoleMapper;
import com.example.qlkh.utils.EbsSecurityUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final WarehouseRepository warehouseRepository;
    private final AuthorityRepository authorityRepository;
    private final RoleAuthorityConnectRepository roleAuthorityConnectRepository;
    private final UserRoleConnectRepository userRoleConnectRepository;
    private final RoleMapper roleMapper;
    private final AuthorityMapper authorityMapper;

    @Override
    public Page<AuthorityRespDto> searchAuthorities(@NonNull SearchDto dto) {
        EbsPrincipal principal = EbsSecurityUtils.getCurrentUser();
        List<Authority> authorities = authorityRepository.search(dto, principal);
        List<AuthorityRespDto> content = authorities.stream().map(authorityMapper::entityToDto).collect(Collectors.toList());
        long total = authorityRepository.count(dto, principal);
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Override
    public RoleRespDto getById(@NonNull Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(RoleStatus.ID_NOT_EXIST));

        return roleMapper.entityToDto(role, role.getWarehouse(), role.getAuthorities());
    }

    @Override
    public Page<RoleRespDto> search(@NonNull SearchDto dto) {
        EbsPrincipal principal = EbsSecurityUtils.getCurrentUser();
        List<Role> roles = roleRepository.search(dto, principal);
        List<RoleRespDto> content = roles.stream().map(roleMapper::entityToDto).collect(Collectors.toList());
        long total = roleRepository.count(dto, principal);
        return new PageImpl<>(content, dto.getPageable(), total);
    }

    @Transactional(rollbackOn = {Exception.class})
    @Override
    public RoleRespDto save(RoleReqDto dto) {
        boolean nonePermission = !EbsSecurityUtils.hasWhPermission(dto.getWarehouseId());
        if (nonePermission) {
            throw new DataRuntimeException(RoleStatus.ACCESS_DENIED_CREATE);
        }

        dto.setId(null);
        this.validateDto(dto);
        Role role = new Role();
        this.mapDtoToEntity(dto, role);

        role = roleRepository.save(role);

        return roleMapper.entityToDto(role, role.getWarehouse(), role.getAuthorities());
    }

    @Transactional(rollbackOn = {Exception.class})
    @Override
    public RoleRespDto update(Long id, RoleReqDto dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(RoleStatus.ID_NOT_EXIST));

        boolean nonePermission = !EbsSecurityUtils.hasWhPermission(dto.getWarehouseId());
        if (nonePermission) {
            throw new DataRuntimeException(RoleStatus.ACCESS_DENIED_UPDATE);
        }

        dto.setId(id);
        this.validateDto(dto);
        this.deleteAuthorities(role, dto);
        this.mapDtoToEntity(dto, role);

        role = roleRepository.save(role);

        return roleMapper.entityToDto(role, role.getWarehouse(), role.getAuthorities());
    }

    @Transactional(rollbackOn = {Exception.class})
    @Override
    public boolean deleteById(@NonNull Long id) {
        Role entity = roleRepository.findById(id)
                .orElseThrow(() -> new DataRuntimeException(RoleStatus.ID_NOT_EXIST));

        Long warehouseId = entity.getWarehouse() != null ? entity.getWarehouse().getId() : null;
        boolean nonePermission = !EbsSecurityUtils.hasWhPermission(warehouseId);
        if (nonePermission) {
            throw new DataRuntimeException(RoleStatus.ACCESS_DENIED_UPDATE);
        }

        userRoleConnectRepository.deleteByRoleId(id);
        roleAuthorityConnectRepository.deleteByRoleId(id);
        roleRepository.delete(entity);

        return true;
    }

    private void deleteAuthorities(@NonNull Role entity, @NonNull RoleReqDto dto) {
        if (CollectionUtils.isNotEmpty(entity.getAuthorities())) {
            if (CollectionUtils.isEmpty(dto.getAuthorities())) {
                roleAuthorityConnectRepository.deleteByRoleId(entity.getId());
            } else {
                roleAuthorityConnectRepository.deleteByRoleId(entity.getId(), dto.getAuthorities());
            }
        }
    }

    private void mapDtoToEntity(RoleReqDto dto, Role entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        if (dto.getWarehouseId() != null) {
            Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                    .orElseThrow(() -> new DataRuntimeException(RoleStatus.WAREHOUSE_ID_IS_NOT_EXIST));
            entity.setWarehouse(warehouse);
        }

        if (CollectionUtils.isNotEmpty(dto.getAuthorities())) {
            List<Authority> authorities = authorityRepository.getByNames(dto.getAuthorities());
            if (CollectionUtils.isNotEmpty(entity.getAuthorities())) {
                List<Authority> authoritieOlds = entity.getAuthorities().stream()
                        .map(RoleAuthorityConnect::getAuthority).collect(Collectors.toList());
                authoritieOlds.forEach(authorities::remove);
            }

            List<RoleAuthorityConnect> roleAuthorityConnects = new ArrayList<>();
            for (Authority authority : authorities) {
                RoleAuthorityConnect roleAuthorityConnect = new RoleAuthorityConnect();
                roleAuthorityConnect.setAuthority(authority);
                roleAuthorityConnect.setRole(entity);
                roleAuthorityConnects.add(roleAuthorityConnect);
            }
            entity.setAuthorities(roleAuthorityConnects);
        }
    }

    public void validateDto(RoleReqDto dto) {
        if (StringUtils.isEmpty(dto.getName())) {
            throw new DataRuntimeException(RoleStatus.NAME_IS_EMPTY);
        }

        boolean hasName = roleRepository.hasName(dto.getId(), dto.getName());
        if (hasName) {
            throw new DataRuntimeException(RoleStatus.NAME_IS_EXIST);
        }

        if (dto.getWarehouseId() != null
                && !warehouseRepository.existsById(dto.getWarehouseId())) {
            throw new DataRuntimeException(RoleStatus.WAREHOUSE_ID_IS_NOT_EXIST);
        }
    }
}
