package com.example.qlkh.error.status;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus implements ErrorStatus {
    ID_NOT_FOUND(400, "errors.user.id_not_found"),
    TOKEN_IS_EXPIRED(400, "errors.user.token_is_expired"),
    IS_NOT_TOKEN(400, "errors.user.this_is_not_token"),
    EMAIL_IS_WRONG_FORMAT(400, "errors.user.wrong_email_format"),
    CONFIRM_PASSWORD_IS_ERROR(400, "errors.user.confirm_password_is_error"),
    EMAIL_IS_EXIST(430_005, "errors.user.email_is_exist"),
    USERNAME_IS_EMPTY(400, "errors.user.username_is_empty"),
    EMAIL_IS_EMPTY(400, "errors.user.email_is_empty"),
    PASSWORD_IS_EMPTY(400, "errors.user.password_is_empty"),
    HAS_USED(400, "errors.user.has_used"),
    EMPLOYEE_HAS_ACCOUNT(400, "errors.user.employee_has_account"),
    EMPLOYEE_ID_IS_NULL(430_003, "errors.user.employee_id_is_null"),
    EMPLOYEE_ID_NOT_FOUND(400, "errors.user.employee_id_not_found"),
    ;

    private final int code;
    private final String message;

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
