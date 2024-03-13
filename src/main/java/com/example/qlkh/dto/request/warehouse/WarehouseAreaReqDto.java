package com.example.qlkh.dto.request.warehouse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class WarehouseAreaReqDto {
    private Long id;
    private String code;
    private String name;
    private WarehouseReqDto warehouse;
    private Boolean deleted;
    private Map<String,WarehouseLocationReqDto> addLocations;
    private List<Long> deleteLocationIds;
}
