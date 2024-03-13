package com.example.qlkh.entity.warehouse.exports;

import com.example.qlkh.entity.FileDescription;
import com.example.qlkh.entity.pk.PkWhExportFileConnect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tbl_warehouse_export_file_connect")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PkWhExportFileConnect.class)
public class WhExportFileConnect implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wh_export_id", referencedColumnName = "id")
    private WhExport whExport;
    @Id
    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private FileDescription file;
}
