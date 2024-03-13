package com.example.qlkh.service.products;

import com.example.qlkh.dto.request.products.ProductUnitReqDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.dto.response.products.ProductUnitRespDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;

public interface ProductUnitService {
    ProductUnitRespDto getById(@NonNull Long id);

    ProductUnitRespDto getDefault(@NonNull Long productId);

    Page<ProductUnitRespDto> search(SearchDto dto);

    ProductUnitRespDto create(@NonNull ProductUnitReqDto dto);

    ProductUnitRespDto update(@NonNull Long id, @NonNull ProductUnitReqDto dto);

    boolean deleteById(@NonNull Long id);
}
