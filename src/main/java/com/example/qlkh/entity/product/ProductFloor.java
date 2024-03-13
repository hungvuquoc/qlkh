package com.example.qlkh.entity.product;

import com.example.qlkh.entity.warehouse.WarehouseFloor;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tbl_product_floor")
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
public class ProductFloor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "floor_id", referencedColumnName = "id")
    private WarehouseFloor floor;
    @ManyToOne
    @JoinColumn(name = "unit_target_id", referencedColumnName = "id")
    private ProductUnitConnect unitTarget;
    @ManyToOne
    @JoinColumn(name = "unit_source_id", referencedColumnName = "id")
    private ProductUnitConnect unitSource;
    @ManyToOne
    @JoinColumn(name = "product_detail_id", referencedColumnName = "id")
    private ProductDetail productDetail;
    @Column(name = "consignment_number")
    private String consignment;
    @Column(name = "quantity_target")
    private Double quantityTarget;
    @Column(name = "quantity_source")
    private Double quantitySource;
    @Column(name = "input_date")
    private LocalDateTime inputDate;
}
