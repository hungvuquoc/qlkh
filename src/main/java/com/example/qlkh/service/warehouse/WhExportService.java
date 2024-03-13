package com.example.qlkh.service.warehouse;

import com.example.qlkh.dto.request.searchs.WhExportSearchReqDto;
import com.example.qlkh.dto.request.warehouse.exports.WhExportReqDto;
import com.example.qlkh.dto.response.warehouse.exports.WhExportRespDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;

public interface WhExportService {
    WhExportRespDto getById(@NonNull Long id);

    Page<WhExportRespDto> search(@NonNull WhExportSearchReqDto dto);

    WhExportRespDto create(@NonNull WhExportReqDto dto);

    WhExportRespDto update(@NonNull Long id, @NonNull WhExportReqDto dto);

    Boolean deleteById(@NonNull Long id);
}
