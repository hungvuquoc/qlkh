package com.example.qlkh.entity.product;

import com.example.qlkh.entity.pk.product.PkProductGroupConnect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tbl_product_group_connect")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PkProductGroupConnect.class)
public class ProductGroupConnect implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @Id
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private ProductGroup group;
}
