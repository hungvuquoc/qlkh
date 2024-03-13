package com.example.qlkh.error.status.warehouse;

import com.example.qlkh.error.status.ErrorStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WhInventoryStatus implements ErrorStatus {
    ID_NOT_FOUND(400, "errors.wh_inventory.id_not_found"),
    WAREHOUSE_ID_NOT_FOUND(400, "errors.wh_inventory.warehouse_id_not_found"),
    EMPLOYEE_ID_NOT_FOUND(400, "errors.wh_inventory.warehouse_id_not_found"),
    EMPLOYEES_IS_EMPTY(400, "errors.wh_inventory.employees_is_empty"),
    SUPPLIER_ID_NOT_FOUND(400, "errors.wh_inventory.supplier_id_not_found"),
    PRODUCT_TYPE_ID_NOT_FOUND(400, "errors.wh_inventory.product_type_id_not_found"),
    INPUT_DATE_IS_NULL(400, "errors.wh_inventory.input_date_is_null"),
    ITEM_INPUT_DATE_IS_NULL(400, "errors.wh_inventory.item_input_date_is_null"),
    STATUS_IS_NULL(400, "errors.wh_inventory.status_is_null"),
    PRODUCT_ID_NOT_FOUND(400, "errors.wh_inventory.product_id_not_found"),
    PRODUCT_DETAIL_ID_NOT_FOUND(400, "errors.wh_inventory.product_detail_id_not_found"),
    UNIT_CONNECT_ID_NOT_FOUND(400, "errors.wh_inventory.unit_target_id_not_found"),
    PRODUCT_NOT_HAS_UNIT_SOURCE_NOT_EXIST(400, "errors.wh_inventory.product_not_has_unit_source_not_exist"),
    QUANTITY_TARGET_IS_NULL(400, "errors.wh_inventory.quantity_target_is_null"),
    QUANTITY_TARGET_WRONG_FORMAT(400, "errors.wh_inventory.quantity_target_wrong_format"),
    QUANTITY_TARGET_NO_SAME_QUANTITY_DETAIL(400, "errors.wh_inventory.quantity_target_no_same_quantity_detail"),
    FLOOR_ID_IS_NULL(400, "errors.wh_inventory.floor_id_is_null"),
    FLOOR_ID_NOT_FOUND(400, "errors.wh_inventory.floor_id_not_found"),
    QUANTITY_DETAIL_IS_NULL(400, "errors.wh_inventory.quantity_detail_is_null"),
    QUANTITY_REAL_IS_WRONG_FORMAT(400, "errors.wh_inventory.quantity_real_is_wrong_format"),
    ITEMS_IS_EMPTY(400, "errors.wh_inventory.items_is_empty"),
    HAS_FINISH(400, "errors.wh_inventory.has_finish"),

    ACCESS_DENIED_CREATE(400, "errors.wh_inventory.access_denied_create"),
    ACCESS_DENIED_UPDATE(400, "errors.wh_inventory.access_denied_update"),
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
