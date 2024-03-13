package com.example.qlkh.dto.request.searchs;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFloorSearchDto extends SearchDto {
    Long locationId; // dùng cho search sơ đồ kho
    Long floorId;
    Long productId;
    String consignment;
    LocalDateTime inputDate;
    Long unitTargetId;
    Long productDetailId;
}
