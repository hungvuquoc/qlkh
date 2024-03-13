package com.example.qlkh.dto.response.warehouse;

import com.example.qlkh.dto.BaseObjectDto;
import com.example.qlkh.dto.response.products.ProductTypeRespDto;
import com.example.qlkh.entity.product.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class WarehouseRespDto extends BaseObjectDto {
    private String code;
    private String name;
    private String address;
    private Integer rowNumber;
    private Integer columnNumber;
    private Boolean deleted;
    private List<WarehouseAreaRespDto> areas;

    private List<ProductTypeRespDto> productTypes;
}
