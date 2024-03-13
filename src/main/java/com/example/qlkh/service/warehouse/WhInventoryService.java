package com.example.qlkh.service.warehouse;

import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.dto.request.warehouse.inventories.WhInventoryReqDto;
import com.example.qlkh.dto.response.warehouse.inventories.WhInventoryRespDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;

public interface WhInventoryService {
    WhInventoryRespDto getById(@NonNull Long id);

    Page<WhInventoryRespDto> search(@NonNull SearchDto dto);

    WhInventoryRespDto create(@NonNull WhInventoryReqDto dto);

    WhInventoryRespDto update(@NonNull Long id, @NonNull WhInventoryReqDto dto);

    Boolean deleteById(@NonNull Long id);
}
