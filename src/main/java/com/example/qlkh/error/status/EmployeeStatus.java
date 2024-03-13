package com.example.qlkh.error.status;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EmployeeStatus implements ErrorStatus {
    ID_NOT_FOUND(400, "errors.employee.id_not_found"),
    HAS_USED(400, "errors.employee.has_used"),
    USER_ID_NOT_FOUND(400, "errors.employee.user_id_not_found"),
    USER_HAS_USED(400, "errors.employee.user_has_used"),

    HAS_ACCOUNT(400, "errors.employee.has_account"),
    ;

    final int code;
    final String message;
    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
