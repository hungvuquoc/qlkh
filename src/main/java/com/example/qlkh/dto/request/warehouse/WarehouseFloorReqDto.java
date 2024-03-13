package com.example.qlkh.dto.request.warehouse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WarehouseFloorReqDto {
    private Long id;
    private String code;
    private String name;
    private WarehouseLocationReqDto warehouseLocation;
    private Boolean deleted;
}
