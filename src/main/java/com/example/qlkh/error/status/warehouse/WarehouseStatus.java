package com.example.qlkh.error.status.warehouse;

import com.example.qlkh.error.status.ErrorStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WarehouseStatus implements ErrorStatus {
    ID_NOT_FOUND(400, "errors.warehouse.id_not_found"),
    AREA_ID_NOT_FOUND(400, "errors.warehouse.area_id_not_found"),
    ID_BEING_USED(400, "errors.warehouse.id_being_used"),
    LOCATION_HAS_USED(400, "errors.warehouse.location_has_used"),
    AREA_HAS_USED(400, "errors.warehouse.area_has_used"),
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
