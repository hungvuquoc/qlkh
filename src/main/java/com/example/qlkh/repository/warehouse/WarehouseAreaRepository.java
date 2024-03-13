package com.example.qlkh.repository.warehouse;

import com.example.qlkh.entity.warehouse.WarehouseArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseAreaRepository extends JpaRepository<WarehouseArea, Long> {
    @Query(
            value = " select right(area.code, 3) from tbl_warehouse_area as area " +
                    " where area.deleted = false " +
                    " and area.code rlike (concat(:warehouseCode, '/')) and area.id not in :areaIds " +
                    " order by right(area.code, 3) desc " +
                    " limit 1 ",
            nativeQuery = true
    )
    String getMaxCode(String warehouseCode, List<Long> areaIds);

    @Query(
            value = " select right(area.code, 3) from tbl_warehouse_area as area " +
                    " where area.deleted = false " +
                    " and area.code rlike (concat(:warehouseCode, '/')) " +
                    " order by right(area.code, 3) desc " +
                    " limit 1 ",
            nativeQuery = true
    )
    String getMaxCode(String warehouseCode);

    @Modifying
    @Query(
            value = " update tbl_warehouse_area as area " +
                    " set area.deleted = true, modify_by = :#{T(com.example.qlkh.utils.EbsSecurityUtils).username}, modify_date = now() " +
                    " where area.id in :ids ",
            nativeQuery = true
    )
    void updateDeletedByIds(List<Long> ids);
}
