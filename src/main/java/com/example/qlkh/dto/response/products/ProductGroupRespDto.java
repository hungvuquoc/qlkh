package com.example.qlkh.dto.response.products;

import com.example.qlkh.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductGroupRespDto extends BaseObjectDto {
    private Long connectId;
    private String code;
    private String name;
    private boolean deleted;
}
