package com.example.qlkh.dto.response.warehouse.imports;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.constant.enums.WhImportType;
import com.example.qlkh.constant.enums.WhTransactionStatus;
import com.example.qlkh.dto.BaseObjectDto;
import com.example.qlkh.dto.FileDescriptionDto;
import com.example.qlkh.dto.response.EmployeeRespDto;
import com.example.qlkh.dto.response.products.ProductTypeRespDto;
import com.example.qlkh.dto.response.products.SupplierRespDto;
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
public class WhImportRespDto extends BaseObjectDto {
    private WhImportType type;
    private String code;
    private String warehouseName;
    private WarehouseRespDto warehouse;
    private EmployeeRespDto employee;
    private SupplierRespDto supplier;
    private ProductTypeRespDto productType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime inputDate;
    private WhTransactionStatus status;
    private String documentNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime documentDate;
    private String containerNumber;
    private String sealNumber;
    private String orderNumber;
    private String note;
    private List<WhImportItemRespDto> items;
    private List<FileDescriptionDto> files;
}
