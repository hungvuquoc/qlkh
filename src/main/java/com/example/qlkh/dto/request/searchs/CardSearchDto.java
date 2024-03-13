package com.example.qlkh.dto.request.searchs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardSearchDto extends SearchDto {
    Long warehouseId;
    Long productId;
}
