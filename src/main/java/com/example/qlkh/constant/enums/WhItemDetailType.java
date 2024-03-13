package com.example.qlkh.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum WhItemDetailType {
    EXPORT(0),
    IMPORT(1),
    ;
    final Integer value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static WhItemDetailType parseByValue(Integer value) {
        if (value != null) {
            for (WhItemDetailType type : values()) {
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
