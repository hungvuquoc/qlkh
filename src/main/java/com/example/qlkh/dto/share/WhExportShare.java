package com.example.qlkh.dto.share;

import com.example.qlkh.entity.Employee;
import com.example.qlkh.entity.product.*;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.entity.warehouse.WarehouseFloor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class WhExportShare {
    private Warehouse warehouse;
    private Employee employee;
    private ProductType productType;
    private Map<Long, Product> products = new HashMap<>();
    private Map<Long, ProductUnitConnect> units = new HashMap<>();
    private Map<Long, ProductDetail> productDetails = new HashMap<>();
    private Map<Long, WarehouseFloor> floors = new HashMap<>();
}
