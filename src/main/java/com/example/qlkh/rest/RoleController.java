package com.example.qlkh.rest;

import com.example.qlkh.dto.request.RoleReqDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.dto.response.AuthorityRespDto;
import com.example.qlkh.dto.response.RoleRespDto;
import com.example.qlkh.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search/authorities")
    public Page<AuthorityRespDto> searchAuthority(SearchDto dto) {
        return roleService.searchAuthorities(dto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public RoleRespDto getById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search")
    public Page<RoleRespDto> search(SearchDto dto) {
        return roleService.search(dto);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public RoleRespDto save(@RequestBody RoleReqDto dto) {
        return roleService.save(dto);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public RoleRespDto update(@PathVariable("id") Long id, @RequestBody RoleReqDto dto) {
        return roleService.update(id, dto);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return roleService.deleteById(id);
    }

    // todo: có cho xóa quyền không, nếu có/không thì phải làm sao
}
