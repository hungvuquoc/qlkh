package com.example.qlkh.error.status.products;

import com.example.qlkh.error.status.ErrorStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProductUnitStatus implements ErrorStatus {
    ID_NOT_FOUND(400, "errors.product_unit.id_not_found"),
    HAS_USED(400, "errors.product_unit.has_used"),
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
