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
public class ProductGroupReqDto extends BaseObjectDto {
    private Long connectId;
    private String code;
    private String name;
    private boolean deleted;
}
