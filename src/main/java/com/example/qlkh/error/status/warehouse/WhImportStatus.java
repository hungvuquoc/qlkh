package com.example.qlkh.error.status.warehouse;

import com.example.qlkh.error.status.ErrorStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WhImportStatus implements ErrorStatus {
    ID_NOT_FOUND(400, "errors.wh_import.id_not_found"),
    WAREHOUSE_ID_NOT_FOUND(400, "errors.wh_import.warehouse_id_not_found"),
    EMPLOYEE_ID_NOT_FOUND(400, "errors.wh_import.employee_id_not_found"),
    SUPPLIER_ID_NOT_FOUND(400, "errors.wh_import.supplier_id_not_found"),
    PRODUCT_TYPE_ID_NOT_FOUND(400, "errors.wh_import.product_type_id_not_found"),
    INPUT_DATE_IS_NULL(400, "errors.wh_import.input_date_is_null"),
    STATUS_IS_NULL(400, "errors.wh_import.status_is_null"),
    PRODUCT_ID_NOT_FOUND(400, "errors.wh_import.product_id_not_found"),
    PRODUCT_DETAIL_ID_NOT_FOUND(400, "errors.wh_import.product_detail_id_not_found"),
    UNIT_TARGET_ID_NOT_FOUND(400, "errors.wh_import.unit_target_id_not_found"),
    PRODUCT_NOT_HAS_UNIT_SOURCE_NOT_EXIST(400, "errors.wh_import.product_not_has_unit_source_not_exist"),
    QUANTITY_TARGET_IS_NULL(400, "errors.wh_import.quantity_target_is_null"),
    QUANTITY_TARGET_WRONG_FORMAT(400, "errors.wh_import.quantity_target_wrong_format"),
    QUANTITY_TARGET_NO_SAME_QUANTITY_DETAIL(400, "errors.wh_import.quantity_target_no_same_quantity_detail"),
    FLOOR_ID_IS_NULL(400, "errors.wh_import.floor_id_is_null"),
    QUANTITY_DETAIL_IS_NULL(400, "errors.wh_import.quantity_detail_is_null"),
    QUANTITY_DETAIL_WRONG_FORMAT(400, "errors.wh_import.quantity_detail_wrong_format"),
    HAS_FINISH(400, "errors.wh_import.has_finish"),
    TYPE_IS_NULL(400, "errors.wh_import.type_is_null"),

    ACCESS_DENIED_CREATE(400, "errors.wh_import.access_denied_create"),
    ACCESS_DENIED_UPDATE(400, "errors.wh_import.access_denied_update"),
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
