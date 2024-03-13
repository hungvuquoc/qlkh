package com.example.qlkh.service.warehouse;

import com.example.qlkh.dto.request.searchs.WhTransferSearchReqDto;
import com.example.qlkh.dto.request.warehouse.transfers.WhTransferReqDto;
import com.example.qlkh.dto.response.warehouse.transfers.WhTransferRespDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;

public interface WhTransferService {
    WhTransferRespDto getById(@NonNull Long id);

    Page<WhTransferRespDto> search(@NonNull WhTransferSearchReqDto dto);

    WhTransferRespDto create(@NonNull WhTransferReqDto dto);

    WhTransferRespDto update(@NonNull Long id, @NonNull WhTransferReqDto dto);

    Boolean deleteById(@NonNull Long id);
}
