package com.example.qlkh.repository.products;

import com.example.qlkh.entity.pk.product.PkProductGroupConnect;
import com.example.qlkh.entity.product.ProductGroupConnect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGroupConnectRepository extends JpaRepository<ProductGroupConnect, PkProductGroupConnect> {
    @Modifying
    @Query(
            value = " delete from tbl_product_group_connect as pg " +
                    " where pg.product_id = :productId " +
                    " and pg.group_id in :groupIds ",
            nativeQuery = true
    )
    void deleteByProductIdAndGroupIds(Long productId, List<Long> groupIds);
}
