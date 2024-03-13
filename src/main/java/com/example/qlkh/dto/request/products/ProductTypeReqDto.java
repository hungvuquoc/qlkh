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
public class ProductTypeReqDto extends BaseObjectDto {
    private String code;
    private String name;
    private boolean deleted;
}
