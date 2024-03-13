package com.example.qlkh.dto.response.projection;

import com.example.qlkh.constant.DateConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public interface ProductFloorProjectionDto {
    Long getId();
    String getMapPoint();
    Long getFloorId();
    String getFloorName();
    @JsonFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    LocalDateTime getInputDate();
    Double getQuantityTarget();
    Double getQuantitySource();
}
