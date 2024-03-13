package com.example.qlkh.dto.request.searchs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTypeSearchDto extends SearchDto {
    private Long[] warehouseIds;
}
