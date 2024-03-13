package com.example.qlkh.dto.response.warehouse.inventories;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.constant.enums.WhInventoryStatus;
import com.example.qlkh.dto.BaseObjectDto;
import com.example.qlkh.dto.FileDescriptionDto;
import com.example.qlkh.dto.response.EmployeeRespDto;
import com.example.qlkh.dto.response.products.ProductTypeRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseRespDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class WhInventoryRespDto extends BaseObjectDto {
    private WarehouseRespDto warehouse;
    private ProductTypeRespDto productType;
    private WhInventoryStatus status;
    @JsonFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime inputDate;
    private String note;
    private List<WhInventoryItemRespDto> items;
    private List<EmployeeRespDto> employees;
    private List<FileDescriptionDto> files;
}
