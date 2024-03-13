package com.example.qlkh.error.status;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoleStatus implements ErrorStatus{
    ID_NOT_EXIST(400, "errors.role.id_not_exist"),
    NAME_IS_EXIST(400, "errors.role.name_is_exist"),
    NAME_IS_EMPTY(400, "errors.role.name_is_exist"),
    WAREHOUSE_ID_IS_NOT_EXIST(400, "errors.role.warehouse_id_is_not_exist"),
    AUTHORITY_IS_EMPTY(400, "errors.role.authority_is_empty"),

    ACCESS_DENIED_CREATE(400, "errors.role.access_denied_create"),
    ACCESS_DENIED_UPDATE(400, "errors.role.access_denied_update"),


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
