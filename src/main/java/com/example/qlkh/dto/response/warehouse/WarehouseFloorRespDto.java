package com.example.qlkh.dto.response.warehouse;

import com.example.qlkh.dto.BaseObjectDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WarehouseFloorRespDto extends BaseObjectDto {
    private String code;
    private String name;
    private String tag;
    private WarehouseLocationRespDto warehouseLocation;
    private Boolean deleted;
}
