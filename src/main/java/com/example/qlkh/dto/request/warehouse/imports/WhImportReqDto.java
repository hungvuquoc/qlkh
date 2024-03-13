package com.example.qlkh.dto.request.warehouse.imports;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.constant.enums.WhImportType;
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
public class WhImportReqDto extends BaseObjectDto {
    private WhImportType type;
    private Long warehouseId;
    private Long employeeId;
    private Long supplierId;
    private Long productTypeId;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime inputDate;
    private WhTransactionStatus status;
    private String documentNumber;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime documentDate;
    private String containerNumber;
    private String sealNumber;
    private String orderNumber;
    private String note;
    private List<WhImportItemReqDto> items;
}
