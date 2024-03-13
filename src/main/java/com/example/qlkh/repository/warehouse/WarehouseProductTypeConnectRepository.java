package com.example.qlkh.repository.warehouse;

import com.example.qlkh.entity.pk.PkWarehouseProductTypeConnect;
import com.example.qlkh.entity.warehouse.WarehouseProductTypeConnect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseProductTypeConnectRepository extends JpaRepository<WarehouseProductTypeConnect, PkWarehouseProductTypeConnect> {
    @Modifying
    @Query(
            value = " delete pro_typ_con from tbl_warehouse_product_type_connect as pro_typ_con " +
                    " where pro_typ_con.warehouse_id = :warehouseId ",
            nativeQuery = true
    )
    void deleteByWarehouseId(Long warehouseId);

    @Modifying
    @Query(
            value = " delete pro_typ_con from tbl_warehouse_product_type_connect as pro_typ_con " +
                    " where pro_typ_con.warehouse_id = :warehouseId and pro_typ_con.type_id not in :productTypeIds ",
            nativeQuery = true
    )
    void deleteByWarehouseId(Long warehouseId, List<Long> productTypeIds);
}
