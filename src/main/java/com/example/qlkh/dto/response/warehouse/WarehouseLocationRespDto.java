package com.example.qlkh.dto.response.warehouse;

import com.example.qlkh.dto.BaseObjectDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class WarehouseLocationRespDto extends BaseObjectDto {
    private String code;
    private String name;
    private WarehouseAreaRespDto warehouseArea;
    private String mapPoint;
    private Boolean deleted;
    private Integer numberOfFloor;
    private List<WarehouseFloorRespDto> floors;
}
