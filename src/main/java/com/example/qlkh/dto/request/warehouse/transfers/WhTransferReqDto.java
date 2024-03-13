package com.example.qlkh.dto.request.warehouse.transfers;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.constant.enums.WhTransactionStatus;
import com.example.qlkh.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WhTransferReqDto extends BaseObjectDto {
    private Long warehouseImportId;
    private Long warehouseExportId;
    private Long productTypeId;
    private WhTransactionStatus status;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE)
    private LocalDateTime inputDate;
    private String documentNumber;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE)
    private LocalDateTime documentDate;
    private String note;
    private List<WhTransferItemReqDto> items;
}
