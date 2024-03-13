package com.example.qlkh.service;


import com.example.qlkh.dto.request.RoleReqDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.dto.response.AuthorityRespDto;
import com.example.qlkh.dto.response.RoleRespDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;

public interface RoleService {
    Page<AuthorityRespDto> searchAuthorities(@NonNull SearchDto dto);

    RoleRespDto getById(@NonNull Long id);

    Page<RoleRespDto> search(@NonNull SearchDto dto);

    RoleRespDto save(RoleReqDto dto);

    RoleRespDto update(Long id, RoleReqDto dto);

    boolean deleteById(@NonNull Long id);
}
