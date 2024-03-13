package com.example.qlkh.entity.warehouse.imports;

import com.example.qlkh.entity.FileDescription;
import com.example.qlkh.entity.pk.PkWhImportFileConnect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tbl_warehouse_import_file_connect")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PkWhImportFileConnect.class)
public class WhImportFileConnect implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wh_import_id", referencedColumnName = "id")
    private WhImport whImport;
    @Id
    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private FileDescription file;
}
