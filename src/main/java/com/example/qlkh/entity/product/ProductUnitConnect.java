package com.example.qlkh.entity.product;

import com.example.qlkh.entity.BaseObject;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "tbl_product_unit_connect")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductUnitConnect extends BaseObject {
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private ProductUnit unit;
    private Double ratio;
    @Column(name = "is_default")
    private boolean isDefault;
    @Column(name = "is_use_report")
    private boolean isUseReport;
    private boolean deleted;
}
