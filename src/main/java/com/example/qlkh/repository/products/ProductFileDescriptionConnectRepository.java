package com.example.qlkh.repository.products;

import com.example.qlkh.entity.pk.product.PkProductFileDescriptionConnect;
import com.example.qlkh.entity.product.ProductFileDescriptionConnect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFileDescriptionConnectRepository extends JpaRepository<ProductFileDescriptionConnect, PkProductFileDescriptionConnect> {
    @Query(
            value = " select * from tbl_product_file_description_connect as pf " +
                    " where pf.product_id = :productId " +
                    " and pf.file_id = :fileId",
            nativeQuery = true
    )
    ProductFileDescriptionConnect getByIdAndFileId(Long productId, Long fileId);
}
