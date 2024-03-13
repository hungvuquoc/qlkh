package com.example.qlkh.dto.request.searchs;

import com.example.qlkh.constant.enums.WhTransactionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhExportSearchReqDto extends SearchDto {
    private Long warehouseId;
    private Long productTypeId;
    private WhTransactionStatus status;
}
