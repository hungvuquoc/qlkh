package com.example.qlkh.entity.warehouse.inventories;

import com.example.qlkh.entity.BaseObject;
import com.example.qlkh.entity.product.Product;
import com.example.qlkh.entity.product.ProductDetail;
import com.example.qlkh.entity.product.ProductUnitConnect;
import com.example.qlkh.entity.warehouse.WarehouseFloor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "tbl_warehouse_inventory_item")
@AllArgsConstructor
@NoArgsConstructor
public class WhInventoryItem extends BaseObject {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private WhInventory parent;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "unit_check_id", referencedColumnName = "id")
    private ProductUnitConnect unitCheck;
    @ManyToOne
    @JoinColumn(name = "product_detail_id", referencedColumnName = "id")
    private ProductDetail productDetail;
    @ManyToOne
    @JoinColumn(name = "floor_id", referencedColumnName = "id")
    private WarehouseFloor floor;
    @Column(name = "input_date")
    private LocalDateTime inputDate;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "consignment_number")
    private String consignmentNumber;
    @Column(name = "quantity_real")
    private Double quantityReal;
    @Column(name = "quantity_fake")
    private Double quantityFake;
    @Column(name = "price")
    private Double price;
}
