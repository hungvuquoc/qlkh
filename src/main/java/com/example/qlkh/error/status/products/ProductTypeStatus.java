package com.example.qlkh.error.status.products;

import com.example.qlkh.error.status.ErrorStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProductTypeStatus implements ErrorStatus {
    ID_NOT_FOUND(400, "errors.product_type.id_not_found"),
    ID_NOT_EXISTS(400, "errors.product_type.id_not_exists"),
    HAS_USED(400, "errors.product_type.has_used"),
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
