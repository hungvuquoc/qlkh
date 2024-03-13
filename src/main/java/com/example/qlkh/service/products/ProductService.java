package com.example.qlkh.service.products;

import com.example.qlkh.dto.request.products.ProductReqDto;
import com.example.qlkh.dto.request.searchs.ProductSearchDto;
import com.example.qlkh.dto.response.products.ProductDetailRespDto;
import com.example.qlkh.dto.response.products.ProductRespDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductRespDto getById(@NonNull Long id);

    Page<ProductRespDto> search(@NonNull ProductSearchDto dto);

    List<ProductRespDto> getBy(@NonNull ProductSearchDto dto);

    List<ProductDetailRespDto> getDetails(@NonNull Long id);

    ProductRespDto create(@NonNull ProductReqDto dto);

    ProductRespDto update(@NonNull Long id, @NonNull ProductReqDto dto);

    boolean deleteById(@NonNull Long id);
}
