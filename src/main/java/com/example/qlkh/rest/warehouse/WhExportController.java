package com.example.qlkh.rest.warehouse;

import com.example.qlkh.dto.request.searchs.WhExportSearchReqDto;
import com.example.qlkh.dto.request.warehouse.exports.WhExportReqDto;
import com.example.qlkh.dto.response.warehouse.exports.WhExportRespDto;
import com.example.qlkh.service.warehouse.WhExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wh-exports")
@RequiredArgsConstructor
public class WhExportController {
    private final WhExportService whExportService;

    @GetMapping("/{id}")
    public WhExportRespDto getById(@PathVariable Long id) {
        return whExportService.getById(id);
    }

    @GetMapping("/search")
    public Page<WhExportRespDto> search(WhExportSearchReqDto dto) {
        return whExportService.search(dto);
    }

    @PostMapping
    public WhExportRespDto create(@RequestBody WhExportReqDto dto) {
        return whExportService.create(dto);
    }

    @PutMapping("/{id}")
    public WhExportRespDto update(@PathVariable Long id, @RequestBody WhExportReqDto dto) {
        return whExportService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return whExportService.deleteById(id);
    }
}
