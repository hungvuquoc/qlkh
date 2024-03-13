package com.example.qlkh.dto.request.warehouse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class WarehouseReqDto {
    private Long id;
    private String code;
    private String name;
    private String address;
    private Integer rowNumber;
    private Integer columnNumber;
    private Boolean deleted;
    private List<WarehouseAreaReqDto> areas;
    private List<WarehouseAreaReqDto> addAreas;
    private List<Long> deleteAreaIds;

    private List<Long> productTypeIds;
}
