package com.example.qlkh.service.warehouse;

import com.example.qlkh.dto.request.searchs.WhImportSearchReqDto;
import com.example.qlkh.dto.request.warehouse.imports.WhImportReqDto;
import com.example.qlkh.dto.response.warehouse.imports.WhImportRespDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;

public interface WhImportService {
    WhImportRespDto getById(@NonNull Long id);

    Page<WhImportRespDto> search(@NonNull WhImportSearchReqDto dto);

    WhImportRespDto create(@NonNull WhImportReqDto dto);

    WhImportRespDto update(@NonNull Long id, @NonNull WhImportReqDto dto);

    Boolean deleteById(@NonNull Long id);
}
