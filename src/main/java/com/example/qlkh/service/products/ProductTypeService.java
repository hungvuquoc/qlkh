package com.example.qlkh.service.products;

import com.example.qlkh.dto.request.products.ProductTypeReqDto;
import com.example.qlkh.dto.response.products.ProductTypeRespDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;

public interface ProductTypeService {
    ProductTypeRespDto getBy(@NonNull Long id);
    Page<ProductTypeRespDto> search(@NonNull SearchDto dto);
    ProductTypeRespDto create(@NonNull ProductTypeReqDto dto);
    ProductTypeRespDto update(@NonNull Long id, @NonNull ProductTypeReqDto dto);
    Boolean delete(@NonNull Long id);
}
