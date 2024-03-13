package com.example.qlkh.repository.warehouse;

import com.example.qlkh.entity.warehouse.WarehouseFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseFloorRepository extends JpaRepository<WarehouseFloor, Long> {
    @Modifying
    @Query(
            value = " delete wf " +
                    " from tbl_warehouse_floor as wf " +
                    " join tbl_warehouse_location as wl on wl.id = wf.warehouse_location_id " +
                    " where wl.id in :ids ",
            nativeQuery = true
    )
    void deleteByLocationIds(List<Long> ids);

    @Modifying
    @Query(
            value = " update tbl_warehouse_floor as flo " +
                    " set flo.deleted = true, modify_by = :#{T(com.example.qlkh.utils.EbsSecurityUtils).username}, modify_date = now() " +
                    " where flo.warehouse_location_id in :locationIds ",
            nativeQuery = true
    )
    void updateDeletedByLocationIds(List<Long> locationIds);

    @Modifying
    @Query(
            value = " update tbl_warehouse_floor as flo " +
                    " join tbl_warehouse_location as loc on flo.warehouse_location_id = loc.id " +
                    " set flo.deleted = true, modify_by = :#{T(com.example.qlkh.utils.EbsSecurityUtils).username}, modify_date = now() " +
                    " where loc.warehouse_area_id in :areaIds ",
            nativeQuery = true
    )
    void updateDeletedByAreaIds(List<Long> areaIds);
}
