package com.example.qlkh.dto.response.warehouse.exports;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.constant.enums.WhExportType;
import com.example.qlkh.constant.enums.WhTransactionStatus;
import com.example.qlkh.dto.BaseObjectDto;
import com.example.qlkh.dto.FileDescriptionDto;
import com.example.qlkh.dto.response.EmployeeRespDto;
import com.example.qlkh.dto.response.products.ProductTypeRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseRespDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WhExportRespDto extends BaseObjectDto {
    private WhExportType type;
    private String code;
    private WarehouseRespDto warehouse;
    private EmployeeRespDto employee;
    private ProductTypeRespDto productType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime inputDate;
    private WhTransactionStatus status;
    private String documentNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime documentDate;
    private String containerNumber;
    private String sealNumber;
    private String quality;
    private String consumerName;
    private String orderNumber;
    private String note;
    private List<WhExportItemRespDto> items;
    private List<FileDescriptionDto> files;
}
