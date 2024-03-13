package com.example.qlkh.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum WhInventoryStatus {
    CHECK_WAITING(1, 1),
    CHECK_PROCESSING(2, 2),
    CHECK_FINISHED(3, 3),
    APPROVAL_WAITING(4, 4),
    APPROVAL_FINISHED(5, 5),

    ;
    final Integer value;
    final Integer sortBy;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static WhInventoryStatus parseByValue(Integer value) {
        if (value != null) {
            for (WhInventoryStatus type : values()) {
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
