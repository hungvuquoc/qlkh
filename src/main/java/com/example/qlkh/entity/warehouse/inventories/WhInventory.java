package com.example.qlkh.entity.warehouse.inventories;

import com.example.qlkh.constant.enums.WhInventoryStatus;
import com.example.qlkh.constant.enums.converters.WhInventoryStatusAttributeConverter;
import com.example.qlkh.entity.BaseObject;
import com.example.qlkh.entity.product.ProductType;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.entity.warehouse.exports.WhExport;
import com.example.qlkh.entity.warehouse.imports.WhImport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tbl_warehouse_inventory")
@AllArgsConstructor
@NoArgsConstructor
public class WhInventory extends BaseObject {
    @Column(name = "code")
    private String code;
    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;
    @ManyToOne
    @JoinColumn(name = "wh_import_id", referencedColumnName = "id")
    private WhImport whImport;
    @ManyToOne
    @JoinColumn(name = "wh_export_id", referencedColumnName = "id")
    private WhExport whExport;
    @Column(name = "wh_code")
    private String whCode;
    @Column(name = "wh_name")
    private String whName;
    @ManyToOne
    @JoinColumn(name = "product_type_id", referencedColumnName = "id")
    private ProductType productType;
    @Convert(converter = WhInventoryStatusAttributeConverter.class)
    private WhInventoryStatus status;
    @Column(name = "input_date")
    private LocalDateTime inputDate;
    @Column(name = "note")
    private String note;
    @OneToMany(mappedBy = "parent")
    private List<WhInventoryItem> items;
    @OneToMany(mappedBy = "inventory")
    private List<WhInventoryEmployeeConnect> employees;
    @OneToMany(mappedBy = "inventory")
    private List<WhInventoryFileConnect> files;
}
