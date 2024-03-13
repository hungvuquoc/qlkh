package com.example.qlkh.entity.warehouse;

import com.example.qlkh.entity.pk.PkWarehouseProductTypeConnect;
import com.example.qlkh.entity.pk.product.PkProductFileDescriptionConnect;
import com.example.qlkh.entity.product.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "tbl_warehouse_product_type_connect")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PkWarehouseProductTypeConnect.class)
public class WarehouseProductTypeConnect {
    @Id
    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;
    @Id
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private ProductType productType;
}
