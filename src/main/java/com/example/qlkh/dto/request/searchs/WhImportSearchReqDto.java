package com.example.qlkh.dto.request.searchs;

import com.example.qlkh.constant.enums.WhTransactionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhImportSearchReqDto extends SearchDto {
    private Long warehouseId;
    private Long supplierId;
    private Long productTypeId;
    private WhTransactionStatus status;
}
