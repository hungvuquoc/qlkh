package com.example.qlkh.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Giới tính
 * */
@AllArgsConstructor
public enum GenderType {
    MALE( 0),
    FEMALE(1),
    LGBT( 2),
    UNKNOWN(3),
    ;

    private final int value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static GenderType findByValue(int value){
        switch (value){
            case 0: return MALE;
            case 1: return FEMALE;
            case 2: return LGBT;
            default: return UNKNOWN;
        }
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
