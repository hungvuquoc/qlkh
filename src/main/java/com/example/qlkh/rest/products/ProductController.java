package com.example.qlkh.rest.products;

import com.example.qlkh.dto.request.products.ProductReqDto;
import com.example.qlkh.dto.response.products.ProductDetailRespDto;
import com.example.qlkh.dto.response.products.ProductRespDto;
import com.example.qlkh.dto.request.searchs.ProductSearchDto;
import com.example.qlkh.service.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductRespDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping("/search")
    public Page<ProductRespDto> search(ProductSearchDto dto) {
        return productService.search(dto);
    }

    @GetMapping("/get-by")
    public List<ProductRespDto> getBy(ProductSearchDto dto) {
        return productService.getBy(dto);
    }

    @GetMapping("/{id}/details")
    public List<ProductDetailRespDto> getDetails(@PathVariable Long id) {
        return productService.getDetails(id);
    }

    @PostMapping("")
    public ProductRespDto create( ProductReqDto dto) {
        return productService.create(dto);
    }

    @PutMapping("/{id}")
    public ProductRespDto update(@PathVariable Long id, ProductReqDto dto) {
        return productService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return productService.deleteById(id);
    }
}
