package com.example.qlkh.dto.response.warehouse.transfers;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.constant.enums.WhTransactionStatus;
import com.example.qlkh.dto.BaseObjectDto;
import com.example.qlkh.dto.response.products.ProductTypeRespDto;
import com.example.qlkh.dto.response.warehouse.WarehouseFloorRespDto;
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
public class WhTransferRespDto extends BaseObjectDto {
    private String code;
    private WarehouseRespDto warehouseImport;
    private WarehouseRespDto warehouseExport;
    private ProductTypeRespDto productType;
    private WhTransactionStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime inputDate;
    private String documentNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime documentDate;
    private String note;
    private List<WhTransferItemRespDto> items;
}
