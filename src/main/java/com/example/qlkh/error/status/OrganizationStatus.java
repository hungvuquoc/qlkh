package com.example.qlkh.error.status;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrganizationStatus implements ErrorStatus {
    NAME_IS_EMPTY(400, "errors.organization.name_is_empty"),
    ENTERPRISE_CODE_IS_EMPTY(400, "errors.organization.enterprise_code_is_empty"),
    ENTERPRISE_WRONG_FORMAT(400, "errors.organization.enterprise_wrong_format"),
    MANAGER_NAME_IS_EMPTY(400, "errors.organization.manager_name_is_empty"),
    ADDRESS_IS_EMPTY(400, "errors.organization.address_is_empty"),
    PHONE_IS_EMPTY(400, "errors.organization.phone_is_empty"),
    PHONE_WRONG_FORMAT(400, "errors.organization.phone_wrong_format"),
    EMAIL_IS_EMPTY(400, "errors.organization.email_is_empty"),
    EMAIL_WRONG_FORMAT(400, "errors.organization.email_wrong_format"),
    DESCRIPTION_IS_EMPTY(400, "errors.organization.description_is_empty"),
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
