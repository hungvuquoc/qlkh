package com.example.qlkh.rest.products;

import com.example.qlkh.dto.request.products.ProductTypeReqDto;
import com.example.qlkh.dto.response.products.ProductTypeRespDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.service.products.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-type")
@RequiredArgsConstructor
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @GetMapping("/{id}")
    public ProductTypeRespDto getBy(@PathVariable("id") Long id) {
        return productTypeService.getBy(id);
    }

    @GetMapping("/search")
    public Page<ProductTypeRespDto> search(SearchDto dto) {
        return productTypeService.search(dto);
    }

    @PostMapping("")
    public ProductTypeRespDto create(@RequestBody ProductTypeReqDto dto) {
        return productTypeService.create(dto);
    }

    @PutMapping("/{id}")
    public ProductTypeRespDto update(@PathVariable("id") Long id, @RequestBody ProductTypeReqDto dto) {
        return productTypeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return productTypeService.delete(id);
    }
}
