package com.example.qlkh.entity.warehouse.transfers;

import com.example.qlkh.entity.FileDescription;
import com.example.qlkh.entity.pk.product.PkWhTransferFileConnect;
import com.example.qlkh.entity.warehouse.imports.WhImport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tbl_warehouse_transfer_file_connect")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PkWhTransferFileConnect.class)
public class WhTransferFileConnect implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wh_transfer_id", referencedColumnName = "id")
    private WhImport whTransfer;
    @Id
    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private FileDescription file;
}
