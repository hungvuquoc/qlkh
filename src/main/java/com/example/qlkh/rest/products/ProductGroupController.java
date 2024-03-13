package com.example.qlkh.rest.products;

import com.example.qlkh.dto.request.products.ProductGroupReqDto;
import com.example.qlkh.dto.response.products.ProductGroupRespDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.service.products.ProductGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-group")
public class ProductGroupController {
    private final ProductGroupService productGroupService;

    @GetMapping("/{id}")
    public ProductGroupRespDto getById(@PathVariable Long id) {
        return productGroupService.getById(id);
    }

    @GetMapping("/search")
    public Page<ProductGroupRespDto> search(SearchDto dto) {
        return productGroupService.search(dto);
    }

    @PostMapping("")
    public ProductGroupRespDto create(@RequestBody ProductGroupReqDto dto) {
        return productGroupService.create(dto);
    }

    @PutMapping("/{id}")
    public ProductGroupRespDto update(@PathVariable Long id, @RequestBody ProductGroupReqDto dto) {
        return productGroupService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return productGroupService.deleteById(id);
    }
}
