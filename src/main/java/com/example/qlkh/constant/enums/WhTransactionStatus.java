package com.example.qlkh.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum WhTransactionStatus {
    INIT(0,0), // khởi tạo phiếu
    FINISH(1, 2), // hoàn thành
    FINISH_EXPORT(2, 1), // hoàn thành xuất

    ;
    final Integer value;
    final Integer sortBy;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static WhTransactionStatus parseByValue(Integer value) {
        if (value != null) {
            for (WhTransactionStatus type : values()) {
                if (Objects.equals(type.value, value)) {
                    return type;
                }
            }
        }
        return null;
    }

    @JsonValue
    public Integer getValue() {
        return value;
    }

    public Integer getSortBy() {
        return sortBy;
    }
}
