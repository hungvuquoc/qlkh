package com.example.qlkh.rest.products;

import com.example.qlkh.dto.request.products.SupplierReqDto;
import com.example.qlkh.dto.response.products.SupplierRespDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.service.products.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping("/{id}")
    public SupplierRespDto getById(@PathVariable Long id) {
        return supplierService.getById(id);
    }

    @GetMapping("/search")
    public Page<SupplierRespDto> search(SearchDto dto) {
        return supplierService.search(dto);
    }

    @PostMapping("")
    public SupplierRespDto create(@RequestBody SupplierReqDto dto) {
        return supplierService.create(dto);
    }

    @PutMapping("/{id}")
    public SupplierRespDto update(@PathVariable Long id, @RequestBody SupplierReqDto dto) {
        return supplierService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return supplierService.deleteById(id);
    }
}
