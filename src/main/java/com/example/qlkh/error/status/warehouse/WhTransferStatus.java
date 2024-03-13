package com.example.qlkh.error.status.warehouse;

import com.example.qlkh.error.status.ErrorStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WhTransferStatus implements ErrorStatus {
    ID_NOT_FOUND(400, "errors.wh_transfer.id_not_found"),
    WAREHOUSE_IMPORT_ID_NOT_FOUND(400, "errors.wh_transfer.warehouse_import_id_not_found"),
    WAREHOUSE_EXPORT_ID_NOT_FOUND(400, "errors.wh_transfer.warehouse_export_id_not_found"),
    EMPLOYEE_ID_NOT_FOUND(400, "errors.wh_transfer.warehouse_id_not_found"),
    SUPPLIER_ID_NOT_FOUND(400, "errors.wh_transfer.supplier_id_not_found"),
    PRODUCT_TYPE_ID_NOT_FOUND(400, "errors.wh_transfer.product_type_id_not_found"),
    INPUT_DATE_IS_NULL(400, "errors.wh_transfer.input_date_is_null"),
    STATUS_IS_NULL(400, "errors.wh_transfer.status_is_null"),
    PRODUCT_ID_NOT_FOUND(400, "errors.wh_transfer.product_id_not_found"),
    PRODUCT_DETAIL_ID_NOT_FOUND(400, "errors.wh_transfer.product_detail_id_not_found"),
    UNIT_TARGET_ID_NOT_FOUND(400, "errors.wh_transfer.unit_target_id_not_found"),
    PRODUCT_NOT_HAS_UNIT_SOURCE_NOT_EXIST(400, "errors.wh_transfer.product_not_has_unit_source_not_exist"),
    QUANTITY_TARGET_IS_NULL(400, "errors.wh_transfer.quantity_target_is_null"),
    QUANTITY_TARGET_WRONG_FORMAT(400, "errors.wh_transfer.quantity_target_wrong_format"),
    FLOOR_ID_IS_NULL(400, "errors.wh_transfer.floor_id_is_null"),
    QUANTITY_DETAIL_IS_NULL(400, "errors.wh_transfer.quantity_detail_is_null"),
    QUANTITY_DETAIL_WRONG_FORMAT(400, "errors.wh_transfer.quantity_detail_wrong_format"),
    HAS_FINISH(400, "errors.wh_transfer.has_finish"),
    NOT_HAVE_PRODUCT(400, "errors.wh_transfer.not_have_product"),
    QUANTITY_IS_NOT_ENOUGH(400, "errors.wh_transfer.quantity_is_not_enough"),

    QUANTITY_TARGET_NO_SAME_QUANTITY_ITEM_EXPORT_DETAIL(400, "errors.wh_transfer.quantity_target_no_same_quantity_item_export_detail"),
    QUANTITY_TARGET_NO_SAME_QUANTITY_ITEM_IMPORT_DETAIL(400, "errors.wh_transfer.quantity_target_no_same_quantity_item_import_detail"),
    QUANTITY_ITEM_IMPORT_NOT_SAME_ITEM_EXPORT(400, "errors.wh_transfer.quantity_item_import_not_same_item_export"),
    ITEM_IMPORT_NOT_SAME_ITEM_EXPORT(400, "errors.wh_transfer.item_import_not_same_item_export"),
    ITEM_IS_EMPTY(400, "errors.wh_transfer.item_is_empty"),
    WH_NOT_HAS_FINISH_PERMISSION(400, "errors.wh_transfer.wh_not_has_finish_permission"),
    WH_EXPORT_UNFINISHED(400, "errors.wh_transfer.wh_export_unfinished"),
    ITEM_DETAIL_EXPORT_IS_EMPTY(400, "errors.wh_transfer.item_detail_export_is_empty"),
    ITEM_DETAIL_IMPORT_IS_EMPTY(400, "errors.wh_transfer.item_detail_import_is_empty"),
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
