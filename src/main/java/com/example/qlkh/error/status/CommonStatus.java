package com.example.qlkh.error.status;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CommonStatus implements ErrorStatus{
    SUCCESS(200, "messages.success"),
    FORBIDDEN(403, "errors.common.access_denied"),
    DATA_INTEGRITY_VIOLATION(403, "errors.common.data_integrity_violation"),

    INTERNAL_SERVER_ERROR(500, "errors.common.internal_server_error"),
    INPUT_OUTPUT_FILE_ERROR(500, "errors.common.io_file_error"),
    FIND_FILE_ERROR(500, "errors.common.find_file_error"),
    DATA_ACCESS_ERROR(500, "errors.common.data_access_error"),
    SQL_WRONG_FORMAT(500, "errors.common.sql_wrong_format"),
    SERVER_SEND_MAIL_ERROR(500, "errors.common.server_send_mail_error"),

    NOT_FOUND(400, "errors.common.not_found"),
    WRONG_FORMAT(400, "errors.common.wrong_format"),
    ACCOUNT_NOT_FOUND(400, "errors.common.account_not_found"),
    TEMPORARY_LOCK(400,"errors.common.temporary_temporaryLock_%d_minutes"),
    TEMPORARY_LOCK_NOT_FINISH(400,"errors.common.the_temporary_lockout_period_is_not_over_yet"),
    ACCOUNT_IS_NOT_ACTIVATED(400, "errors.common.account_is_not_activated"),
    ACCOUNT_HAS_BEEN_LOCKED(400, "errors.common.account_has_been_locked"),
    WRONG_USERNAME_OR_PASSWORD(400, "errors.common.wrong_username_or_password"),
    OBJECT_ALREADY_EXIST(400, "errors.common.object_already_exist"),
    EMAIL_IS_WRONG_FORMAT(400, "errors.common.wrong_email_format"),
    EMAIL_IS_EXIST(400, "errors.common.email_is_exist"),
    SENT_EMAIL_TO_YOU(400, "errors.common.sent_2_emails_to_you"),

    CODE_SMS_IS_EMPTY(400, "errors.common.verify_code_is_empty"),
    CODE_SMS_INVALID(400, "errors.common.verify_code_invalid"),
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
