package com.example.qlkh.dto.request.searchs;

import com.example.qlkh.constant.enums.WhTransactionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhTransferSearchReqDto extends SearchDto {
    private Long warehouseImportId;
    private Long warehouseExportId;
    private Long productTypeId;
    private WhTransactionStatus status;
}
