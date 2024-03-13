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
public class ProductDetailReqDto extends BaseObjectDto {
    private Double price;
    private String quality;
    private String size;
}
