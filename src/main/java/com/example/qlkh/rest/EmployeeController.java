package com.example.qlkh.rest;

import com.example.qlkh.dto.request.EmployeeReqDto;
import com.example.qlkh.dto.request.searchs.EmployeeSearchDto;
import com.example.qlkh.dto.response.EmployeeRespDto;
import com.example.qlkh.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public EmployeeRespDto getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @GetMapping("/search")
    public Page<EmployeeRespDto> search(EmployeeSearchDto dto) {
        return employeeService.search(dto);
    }

    @PostMapping
    public EmployeeRespDto create(@RequestBody EmployeeReqDto dto) {
        return employeeService.create(dto);
    }

    @PutMapping("/{id}")
    public EmployeeRespDto update(@PathVariable Long id, @RequestBody EmployeeReqDto dto) {
        return employeeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return employeeService.deleteById(id);
    }
}
