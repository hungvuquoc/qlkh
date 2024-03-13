package com.example.qlkh.rest.products;

import com.example.qlkh.dto.request.products.ProductUnitReqDto;
import com.example.qlkh.dto.response.products.ProductUnitRespDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.service.products.ProductUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-unit")
public class ProductUnitController {
    private final ProductUnitService productUnitService;

    @GetMapping("/{id}")
    public ProductUnitRespDto getById(@PathVariable Long id) {
        return productUnitService.getById(id);
    }

    @GetMapping("/default/product/{id}")
    public ProductUnitRespDto getDefaultByProductId(@PathVariable Long id) {
        return productUnitService.getDefault(id);
    }

    @GetMapping("/search")
    public Page<ProductUnitRespDto> search(SearchDto dto) {
        return productUnitService.search(dto);
    }

    @PostMapping("")
    public ProductUnitRespDto create(@RequestBody ProductUnitReqDto dto) {
        return productUnitService.create(dto);
    }

    @PutMapping("/{id}")
    public ProductUnitRespDto update(@PathVariable Long id, @RequestBody ProductUnitReqDto dto) {
        return productUnitService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return productUnitService.deleteById(id);
    }
}
