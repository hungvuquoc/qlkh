package com.example.qlkh.entity.warehouse.inventories;

import com.example.qlkh.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tbl_warehouse_inventory_employee_connect")
@AllArgsConstructor
@NoArgsConstructor
public class WhInventoryEmployeeConnect implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wh_inventory_id", referencedColumnName = "id")
    private WhInventory inventory;

    @Id
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
}
