package com.example.qlkh.entity.warehouse.imports;

import com.example.qlkh.entity.product.Product;
import com.example.qlkh.entity.product.ProductDetail;
import com.example.qlkh.entity.product.ProductUnitConnect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_warehouse_import_item")
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
public class WhImportItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private WhImport parent;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "unit_target_id", referencedColumnName = "id")
    private ProductUnitConnect unitTarget;
    @ManyToOne
    @JoinColumn(name = "unit_source_id", referencedColumnName = "id")
    private ProductUnitConnect unitSource;
    @ManyToOne
    @JoinColumn(name = "product_detail_id", referencedColumnName = "id")
    private ProductDetail productDetail;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "consignment_number")
    private String consignmentNumber;
    @Column(name = "quantity_target")
    private Double quantityTarget;
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity_source")
    private Double quantitySource;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<WhImportItemDetail> details;
}
