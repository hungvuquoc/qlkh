package com.example.qlkh.entity.product;

import com.example.qlkh.entity.BaseObject;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tbl_product")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, of = {"code", "name"})
public class Product extends BaseObject {
    @Column(length = 30, unique = true)
    private String code;
    @Column(length = 500)
    private String name;
    @Column(name = "name_print", length = 500)
    private String namePrint;
    @Column(length = 1000)
    private String note;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private ProductType type;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductUnitConnect> units;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductGroupConnect> groups;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductFileDescriptionConnect> images;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductDetail> details;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductSupplierConnect> suppliers;
    private boolean deleted = false;
}
