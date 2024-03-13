package com.example.qlkh.rest.warehouse;

import com.example.qlkh.dto.request.searchs.WhExportSearchReqDto;
import com.example.qlkh.dto.request.searchs.WhTransferSearchReqDto;
import com.example.qlkh.dto.request.warehouse.exports.WhExportReqDto;
import com.example.qlkh.dto.request.warehouse.transfers.WhTransferReqDto;
import com.example.qlkh.dto.response.warehouse.exports.WhExportRespDto;
import com.example.qlkh.dto.response.warehouse.transfers.WhTransferRespDto;
import com.example.qlkh.service.warehouse.WhTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wh-transfers")
@RequiredArgsConstructor
public class WhTransferController {
    private final WhTransferService whTransferService;

    @GetMapping("/{id}")
    public WhTransferRespDto getById(@PathVariable Long id) {
        return whTransferService.getById(id);
    }

    @GetMapping("/search")
    public Page<WhTransferRespDto> search(WhTransferSearchReqDto dto) {
        return whTransferService.search(dto);
    }

    @PostMapping
    public WhTransferRespDto create(@RequestBody WhTransferReqDto dto) {
        return whTransferService.create(dto);
    }

    @PutMapping("/{id}")
    public WhTransferRespDto update(@PathVariable Long id, @RequestBody WhTransferReqDto dto) {
        return whTransferService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return whTransferService.deleteById(id);
    }
}
