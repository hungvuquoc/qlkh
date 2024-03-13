package com.example.qlkh.entity.warehouse.inventories;

import com.example.qlkh.entity.FileDescription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tbl_warehouse_inventory_file_connect")
@AllArgsConstructor
@NoArgsConstructor
public class WhInventoryFileConnect implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wh_inventory_id", referencedColumnName = "id")
    private WhInventory inventory;
    @Id
    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private FileDescription file;
}
