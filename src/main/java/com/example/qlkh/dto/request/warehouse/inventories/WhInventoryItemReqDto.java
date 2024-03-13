package com.example.qlkh.dto.request.warehouse.inventories;

import com.example.qlkh.constant.DateConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class WhInventoryItemReqDto {
    private Long productId;
    private Long unitCheckId;
    private Long productDetailId;
    private Long floorId;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime inputDate;
    private String consignmentNumber;
    private double quantityReal;
}
