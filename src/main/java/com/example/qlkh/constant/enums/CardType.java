package com.example.qlkh.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum CardType {
    EXPORT(0, "Xuất hàng"),
    IMPORT(1, "Nhập hàng"),
    TRANSFER(2, "Điều chuyển")
    ;
    final Integer value;
    final String note;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static CardType parseByValue(Integer value) {
        if (value != null) {
            for (CardType type : values()) {
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

    public String getNote() {
        return note;
    }
}
