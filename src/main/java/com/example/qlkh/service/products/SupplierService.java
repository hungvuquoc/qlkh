package com.example.qlkh.service.products;

import com.example.qlkh.dto.request.products.SupplierReqDto;
import com.example.qlkh.dto.response.products.SupplierRespDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;

public interface SupplierService {
    SupplierRespDto getById(@NonNull Long id);
    Page<SupplierRespDto> search(SearchDto dto);
    SupplierRespDto create(@NonNull SupplierReqDto dto);
    SupplierRespDto update(@NonNull Long id, @NonNull SupplierReqDto dto);
    boolean deleteById(@NonNull Long id);
}
