package com.example.qlkh.dto.request.warehouse.exports;

import com.example.qlkh.constant.DateConstants;
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
public class WhExportItemDetailReqDto implements Serializable {
    private Long id;
    private Long parentId;
    private Long floorId;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime inputDate;
    private Double quantity;
}
