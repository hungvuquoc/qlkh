package com.example.qlkh.dto.request.searchs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSearchDto extends SearchDto{
    Long[] typeIds;
    Long[] groupIds;
    Long[] unitIds;
    Long[] supplierIds;
}
