package com.example.qlkh.dto.request.warehouse.inventories;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.constant.enums.WhInventoryStatus;
import com.example.qlkh.dto.BaseObjectDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class WhInventoryReqDto extends BaseObjectDto {
    private Long warehouseId;
    private Long productTypeId;
    private WhInventoryStatus status;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime inputDate;
    private String note;
    private List<WhInventoryItemReqDto> items;
    private List<Long> employeeIds;
}