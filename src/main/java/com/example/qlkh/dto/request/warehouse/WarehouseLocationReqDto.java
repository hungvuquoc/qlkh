package com.example.qlkh.dto.request.warehouse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class WarehouseLocationReqDto {
    private Long id;
    private String code;
    private String name;
    private WarehouseAreaReqDto warehouseArea;
    private String mapPoint;
    private Boolean deleted;
    private Integer numberOfFloors;
    private List<WarehouseFloorReqDto> floors;
}
