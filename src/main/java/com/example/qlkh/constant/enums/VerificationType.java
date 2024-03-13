package com.example.qlkh.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Kiểu mã xác nhận
 * */
@Getter
@AllArgsConstructor
public enum VerificationType {
    REGISTER(1),
    FORGOT_PASSWORD(2),
    ;

    private final int value;
}