package com.example.qlkh.dto.response.warehouse;

import com.example.qlkh.dto.BaseObjectDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class WarehouseAreaRespDto extends BaseObjectDto {
    private String code;
    private String name;
    private WarehouseRespDto warehouse;
    private Integer numberOfFloors;
    private Boolean deleted;
    private Map<String,WarehouseLocationRespDto> locations;
}
