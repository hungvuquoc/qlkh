package com.example.qlkh.dto.request.warehouse.transfers;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.constant.enums.WhItemDetailType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WhTransferItemDetailReqDto implements Serializable {
    private Long id;
    private Long parentId;
    private Long floorId;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime inputDate;
    private Double quantity;
    private WhItemDetailType type;
}
