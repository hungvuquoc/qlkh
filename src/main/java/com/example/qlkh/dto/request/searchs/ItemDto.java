package com.example.qlkh.dto.request.searchs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemDto {
    private Long productId;
    private Long unitTargetId;
    private Long productDetailId;
    private String consignmentNumber;
    List<ItemDetailDto> details;
}
