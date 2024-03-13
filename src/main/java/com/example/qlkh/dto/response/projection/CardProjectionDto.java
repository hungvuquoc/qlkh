package com.example.qlkh.dto.response.projection;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.constant.enums.CardType;
import com.example.qlkh.utils.serializers.EbsDoubleSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

public interface CardProjectionDto {
    @JsonIgnore
    CardType getType();
//    @JsonFormat(pattern = DateConstants.US_NORMAL_DATE)
    LocalDateTime getCreateDate();
    @JsonIgnore
    String getCode();
    String getNote();
//    @JsonFormat(pattern = DateConstants.US_NORMAL_DATE)
    LocalDateTime getInputDate();
    @JsonIgnore
    Double getQuantity();
//    @JsonSerialize(using = EbsDoubleSerializer.class)
    Double getStockQuantity();

    default String getInCode() {
        if (CardType.IMPORT.equals(getType())) {
            return getCode();
        }

        return "";
    }

    default String getOutCode() {
        if (CardType.EXPORT.equals(getType())) {
            return getCode();
        }

        return "";
    }

//    @JsonSerialize(using = EbsDoubleSerializer.class)
    default Double getInQuantity() {
        if (CardType.IMPORT.equals(getType())) {
            return getQuantity();
        }

        return 0d;
    }

//    @JsonSerialize(using = EbsDoubleSerializer.class)
    default Double getOutQuantity() {
        if (CardType.EXPORT.equals(getType())) {
            return getQuantity();
        }

        return 0d;
    }
}
