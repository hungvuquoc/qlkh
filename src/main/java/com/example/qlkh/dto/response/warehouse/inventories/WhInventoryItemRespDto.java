package com.example.qlkh.dto.response.warehouse.inventories;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.dto.BaseObjectDto;
import com.example.qlkh.dto.response.products.ProductDetailRespDto;
import com.example.qlkh.dto.response.products.ProductRespDto;
import com.example.qlkh.dto.response.products.ProductUnitRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseFloorRespDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WhInventoryItemRespDto extends BaseObjectDto {
    private ProductRespDto product;
    private ProductUnitRespDto unitCheck;
    private ProductDetailRespDto productDetail;
    private WarehouseFloorRespDto floor;
    @JsonFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime inputDate;
    private String consignmentNumber;
    private Double quantityReal;
    private Double quantityFake;
}
