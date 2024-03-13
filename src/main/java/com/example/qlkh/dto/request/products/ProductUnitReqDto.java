package com.example.qlkh.dto.request.products;

import com.example.qlkh.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUnitReqDto extends BaseObjectDto {
    private Long unitId;
    private String code;
    private String name;
    private Double ratio;
    private boolean isDefault;
    private boolean isUseReport;
    private boolean deleted;
}
