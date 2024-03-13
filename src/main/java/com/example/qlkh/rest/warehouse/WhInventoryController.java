package com.example.qlkh.rest.warehouse;

import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.dto.request.warehouse.inventories.WhInventoryReqDto;
import com.example.qlkh.dto.response.warehouse.inventories.WhInventoryRespDto;
import com.example.qlkh.service.warehouse.WhInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("wh-inventories")
public class WhInventoryController {
    private final WhInventoryService whInventoryService;

    @GetMapping("/{id}")
    public WhInventoryRespDto getById(@PathVariable Long id) {
        return whInventoryService.getById(id);
    }

    @GetMapping("/search")
    public Page<WhInventoryRespDto> search(SearchDto dto) {
        return whInventoryService.search(dto);
    }

    @PostMapping
    public WhInventoryRespDto create(@RequestBody WhInventoryReqDto dto) {
        return whInventoryService.create(dto);
    }

    @PutMapping("/{id}")
    public WhInventoryRespDto update(@PathVariable Long id, @RequestBody WhInventoryReqDto dto) {
        return whInventoryService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return whInventoryService.deleteById(id);
    }
}
