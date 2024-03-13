package com.example.qlkh.dto.request.searchs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockSearchDto {
    Long warehouseId;
    Long andInWarehouseId;
    Long typeId;
    Long productId;
    Long productDetailId;
    Long unitId;
    String consignmentNumber;
    Long locationId;
    Boolean deleted;
}
