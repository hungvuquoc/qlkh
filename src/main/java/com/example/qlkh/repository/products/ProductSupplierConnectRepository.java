package com.example.qlkh.repository.products;

import com.example.qlkh.entity.pk.product.PkProductSupplierConnect;
import com.example.qlkh.entity.product.ProductSupplierConnect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSupplierConnectRepository extends JpaRepository<ProductSupplierConnect, PkProductSupplierConnect> {
    @Modifying
    @Query(
            value = " delete from tbl_product_supplier_connect as ps " +
                    " where ps.product_id = :productId " +
                    " and ps.supplier_id in :supplierIds ",
            nativeQuery = true
    )
    void deleteByProductIdAndSupplierIds(Long productId, List<Long> supplierIds);
}
