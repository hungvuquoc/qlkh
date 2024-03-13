package com.example.qlkh.dto.request.warehouse.exports;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.constant.enums.WhExportType;
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
public class WhExportReqDto extends BaseObjectDto {
    private WhExportType type;
    private Long warehouseId;
    private Long employeeId;
    private Long productTypeId;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE)
    private LocalDateTime inputDate;
    private WhTransactionStatus status;
    private String documentNumber;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE)
    private LocalDateTime documentDate;
    private String containerNumber;
    private String sealNumber;
    private String orderNumber;
    private String quality;
    private String consumerName;
    private String note;
    private List<WhExportItemReqDto> items;
}
