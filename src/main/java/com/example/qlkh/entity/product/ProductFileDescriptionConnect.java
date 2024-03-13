package com.example.qlkh.entity.product;

import com.example.qlkh.entity.FileDescription;
import com.example.qlkh.entity.pk.product.PkProductFileDescriptionConnect;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tbl_product_file_description_connect")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PkProductFileDescriptionConnect.class)
public class ProductFileDescriptionConnect implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @Id
    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private FileDescription file;
    @Column(name = "order_by")
    private Integer orderBy;
}
