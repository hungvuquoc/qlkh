package com.example.qlkh.dto.response.projection;

import com.example.qlkh.constant.DateConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public interface ProductFloorDetailProjectionDto {
    String getFloorName();
    String getProductName();
    @JsonIgnore
    Double getDetailPrice();
    @JsonIgnore
    String getDetailQuality();
    @JsonIgnore
    String getDetailSize();
    @JsonIgnore
    String getUnitName();
    @JsonIgnore
    String getUnitRatio();
    @JsonIgnore
    Boolean getUnitDefault();
    @JsonIgnore
    Boolean getUnitReport();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.US_NORMAL_DATE_TIME)
    LocalDateTime getInputDate();
    Double getQuantity();



    default String getProductDetailName() {
        StringBuilder tag = new StringBuilder();
        if (getDetailSize() == null) {
            tag.append("[N/A]");
        } else {
            tag.append("[").append(getDetailSize()).append("]");
        }
        tag.append(" ");
        if (getDetailQuality() == null) {
            tag.append("[N/A]");
        } else {
            tag.append("[").append(getDetailQuality()).append("]");
        }
        tag.append(" ");
        if (getDetailPrice() == null) {
            tag.append("[N/A]");
        } else {
            tag.append("[").append(getDetailPrice()).append("]");
        }

        return tag.toString();
    }

    default String getProductUnitName() {
        StringBuilder tag = new StringBuilder();
        tag.append("[").append(getUnitName()).append("]");
        tag.append(" ");
        tag.append("[").append(getUnitRatio()).append("]");

        if (getUnitDefault()) {
            tag.append(" [Mặc định]");
        }

        if (getUnitReport()) {
            tag.append(" [Báo cáo]");
        }

        return tag.toString();
    }
}
