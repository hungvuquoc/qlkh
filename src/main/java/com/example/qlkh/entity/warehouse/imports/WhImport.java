package com.example.qlkh.entity.warehouse.imports;

import com.example.qlkh.constant.enums.WhImportType;
import com.example.qlkh.constant.enums.WhTransactionStatus;
import com.example.qlkh.constant.enums.converters.WhImportTypeAttributeConverter;
import com.example.qlkh.constant.enums.converters.WhTransactionStatusAttributeConverter;
import com.example.qlkh.entity.BaseObject;
import com.example.qlkh.entity.Employee;
import com.example.qlkh.entity.product.ProductType;
import com.example.qlkh.entity.product.Supplier;
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
@Table(name = "tbl_warehouse_import")
@AllArgsConstructor
@NoArgsConstructor
public class WhImport extends BaseObject {
    @Column(name = "type")
    @Convert(converter = WhImportTypeAttributeConverter.class)
    private WhImportType type;
    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "product_type_id", referencedColumnName = "id")
    private ProductType productType;
    @Column(name = "warehouse_name")
    private String warehouseName;
    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "code")
    private String code;
    @Column(name = "input_date")
    private LocalDateTime inputDate;
    @Column(name = "status")
    @Convert(converter = WhTransactionStatusAttributeConverter.class)
    private WhTransactionStatus status;
    @Column(name = "document_number")
    private String documentNumber;
    @Column(name = "document_date")
    private LocalDateTime documentDate;
    @Column(name = "container_number")
    private String containerNumber;
    @Column(name = "seal_number")
    private String sealNumber;
    @Column(name = "order_number")
    private String orderNumber;
    @Column(name = "note")
    private String note;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<WhImportItem> items;
    @OneToMany(mappedBy = "whImport")
    private List<WhImportFileConnect> files;
}
