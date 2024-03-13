package com.example.qlkh.service.products;

import com.example.qlkh.dto.request.products.ProductGroupReqDto;
import com.example.qlkh.dto.response.products.ProductGroupRespDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;

public interface ProductGroupService {
    ProductGroupRespDto getById(@NonNull Long id);
    Page<ProductGroupRespDto> search(@NonNull SearchDto dto);
    ProductGroupRespDto create(@NonNull ProductGroupReqDto dto);
    ProductGroupRespDto update(@NonNull Long id, @NonNull ProductGroupReqDto dto);
    boolean deleteById(@NonNull Long id);
}
