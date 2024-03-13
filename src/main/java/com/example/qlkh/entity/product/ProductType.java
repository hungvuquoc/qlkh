package com.example.qlkh.entity.product;

import com.example.qlkh.entity.BaseObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "tbl_product_type")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductType extends BaseObject {
    @Column(length = 30, unique = true)
    private String code;
    private String name;
    private boolean deleted = false;
}
