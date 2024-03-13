package com.example.qlkh.entity.warehouse;

import com.example.qlkh.entity.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tbl_warehouse_area")
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseArea extends BaseObject {
    private String code;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;
    private Boolean deleted;
    @OneToMany(mappedBy = "warehouseArea", cascade = CascadeType.ALL)
    private List<WarehouseLocation> locations;
}
