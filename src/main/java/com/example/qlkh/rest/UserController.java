package com.example.qlkh.rest;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.request.UserReqDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.dto.response.UserRespDto;
import com.example.qlkh.dto.response.projection.UserProjectionDto;
import com.example.qlkh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public EbsPrincipal getInfo() {
        return userService.getInfo();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public UserRespDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search")
    public Page<UserProjectionDto> search(SearchDto dto) {
        return userService.search(dto);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public UserRespDto save(@RequestBody UserReqDto dto) {
        return userService.save(dto);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public UserRespDto update(@PathVariable Long id, @RequestBody UserReqDto dto) {
        return userService.update(id, dto);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/logout")
    public boolean logout(HttpServletRequest request) {
        userService.logout(request);
        return true;
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return userService.deleteById(id);
    }
}
