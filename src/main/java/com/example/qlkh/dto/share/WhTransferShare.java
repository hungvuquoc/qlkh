package com.example.qlkh.dto.share;

import com.example.qlkh.constant.enums.WhTransactionStatus;
import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.entity.Employee;
import com.example.qlkh.entity.product.Product;
import com.example.qlkh.entity.product.ProductDetail;
import com.example.qlkh.entity.product.ProductType;
import com.example.qlkh.entity.product.ProductUnitConnect;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.entity.warehouse.WarehouseFloor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class WhTransferShare {
    private WhTransactionStatus status;
    private LocalDateTime inputDate;
    private boolean isImportPermission;
    private Warehouse warehouseImport;
    private boolean isExportPermission;
    private Warehouse warehouseExport;
    private EbsPrincipal userPrincipal;
    private Employee employee;
    private ProductType productType;
    private Map<Long, Product> products = new HashMap<>();
    private Map<Long, ProductUnitConnect> units = new HashMap<>();
    private Map<Long, ProductDetail> productDetails = new HashMap<>();
    private Map<Long, WarehouseFloor> floors = new HashMap<>();

    public boolean isFullPermission() {
        return isExportPermission && isImportPermission;
    }
}
