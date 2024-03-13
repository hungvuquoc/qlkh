package com.example.qlkh.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum WhExportType {
    MANUFACTURE(0),
    SELL(1),
    RETURNS(2),
    ;
    final Integer value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static WhExportType parseByValue(Integer value) {
        if (value != null) {
            for (WhExportType type : values()) {
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
}
