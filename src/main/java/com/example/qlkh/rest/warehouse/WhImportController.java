package com.example.qlkh.rest.warehouse;

import com.example.qlkh.dto.request.searchs.WhImportSearchReqDto;
import com.example.qlkh.dto.request.warehouse.imports.WhImportReqDto;
import com.example.qlkh.dto.response.warehouse.imports.WhImportRespDto;
import com.example.qlkh.service.warehouse.WhImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wh-imports")
@RequiredArgsConstructor
public class WhImportController {
    private final WhImportService whImportService;

    @GetMapping("/{id}")
    public WhImportRespDto getById(@PathVariable Long id) {
        return whImportService.getById(id);
    }

    @GetMapping("/search")
    public Page<WhImportRespDto> search(WhImportSearchReqDto dto) {
        return whImportService.search(dto);
    }

    @PostMapping
    public WhImportRespDto create(@RequestBody WhImportReqDto dto) {
        return whImportService.create(dto);
    }

    @PutMapping("/{id}")
    public WhImportRespDto update(@PathVariable Long id, @RequestBody WhImportReqDto dto) {
        return whImportService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return whImportService.deleteById(id);
    }
}
