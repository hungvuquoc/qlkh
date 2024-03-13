package com.example.qlkh.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum WhImportType {
    BUY(0),
    MANUFACTURE(1),
    ;
    final Integer value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static WhImportType parseByValue(Integer value) {
        if (value != null) {
            for (WhImportType type : values()) {
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
