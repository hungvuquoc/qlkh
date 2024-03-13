package com.example.qlkh.entity.warehouse;

import com.example.qlkh.entity.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "tbl_warehouse_floor")
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseFloor extends BaseObject {
    private String code;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_location_id", referencedColumnName = "id")
    private WarehouseLocation warehouseLocation;
    private Boolean deleted;
}
