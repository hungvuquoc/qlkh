package com.example.qlkh.entity.warehouse.transfers;

import com.example.qlkh.constant.enums.WhTransactionStatus;
import com.example.qlkh.constant.enums.converters.WhTransactionStatusAttributeConverter;
import com.example.qlkh.entity.BaseObject;
import com.example.qlkh.entity.Employee;
import com.example.qlkh.entity.product.ProductType;
import com.example.qlkh.entity.warehouse.Warehouse;
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
@Table(name = "tbl_warehouse_transfer")
@AllArgsConstructor
@NoArgsConstructor
public class WhTransfer extends BaseObject {
    @ManyToOne
    @JoinColumn(name = "wh_import_id", referencedColumnName = "id")
    private Warehouse warehouseImport;
    @ManyToOne
    @JoinColumn(name = "wh_export_id", referencedColumnName = "id")
    private Warehouse warehouseExport;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "product_type_id", referencedColumnName = "id")
    private ProductType productType;
    @Column(name = "wh_import_name")
    private String warehouseImportName;
    @Column(name = "wh_export_name")
    private String warehouseExportName;
    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "code")
    private String code;
    @Column(name = "status")
    @Convert(converter = WhTransactionStatusAttributeConverter.class)
    private WhTransactionStatus status;
    @Column(name = "input_date")
    private LocalDateTime inputDate;
    @Column(name = "document_number")
    private String documentNumber;
    @Column(name = "document_date")
    private LocalDateTime documentDate;
    @Column(name = "note")
    private String note;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<WhTransferItem> items;
    @OneToMany(mappedBy = "whTransfer")
    private List<WhTransferFileConnect> files;
}
