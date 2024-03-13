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
@Table(name = "tbl_warehouse_location")
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseLocation extends BaseObject {
    private String code;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_area_id", referencedColumnName = "id")
    private WarehouseArea warehouseArea;
    @Column(name = "map_point")
    private String mapPoint;
    private Boolean deleted;
    @OneToMany(mappedBy = "warehouseLocation", cascade = CascadeType.ALL)
    private List<WarehouseFloor> floors;
}
