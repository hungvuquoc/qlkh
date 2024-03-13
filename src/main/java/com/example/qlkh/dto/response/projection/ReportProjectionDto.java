package com.example.qlkh.dto.response.projection;

import com.example.qlkh.utils.serializers.EbsDoubleSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public interface ReportProjectionDto {
    Long getProductId();
    String getProductCode();
    String getProductName();
    String getConsignmentNumber();
    String getUnitId();
    String getUnitName();
    @JsonSerialize(using = EbsDoubleSerializer.class)
    Double getStartStockQuantity();
    @JsonSerialize(using = EbsDoubleSerializer.class)
    Double getStartStockCost();
    @JsonSerialize(using = EbsDoubleSerializer.class)
    Double getInQuantity();
    @JsonSerialize(using = EbsDoubleSerializer.class)
    Double getInCost();
    @JsonSerialize(using = EbsDoubleSerializer.class)
    Double getOutQuantity();
    @JsonSerialize(using = EbsDoubleSerializer.class)
    Double getOutCost();
    @JsonSerialize(using = EbsDoubleSerializer.class)
    Double getEndStockQuantity();
    @JsonSerialize(using = EbsDoubleSerializer.class)
    Double getEndStockCost();
}
