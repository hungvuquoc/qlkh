package com.example.qlkh.dto.request.searchs;

import com.example.qlkh.constant.DateConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportSearchDto{
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime endDate;
    private Long warehouseId;
    private Long productTypeId;
    // ReportType
}
