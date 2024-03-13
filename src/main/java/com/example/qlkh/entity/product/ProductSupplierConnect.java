package com.example.qlkh.entity.product;

import com.example.qlkh.entity.pk.product.PkProductSupplierConnect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tbl_product_supplier_connect")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PkProductSupplierConnect.class)
public class ProductSupplierConnect implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @Id
    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;
}
