package com.example.qlkh.repository.warehouse;

import com.example.qlkh.entity.warehouse.WarehouseStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface WarehouseStructureRepository extends JpaRepository<WarehouseStructure, Long> {
    @Query(
            value = " select war_str.* from tbl_warehouse_structure as war_str " +
                    " where war_str.warehouse_id = :warehouseId and (:modifyDate is null or war_str.create_date <= :modifyDate) " +
                    " order by war_str.create_date desc " +
                    " limit 1 ",
            nativeQuery = true
    )
    WarehouseStructure getByWarehouseIdAndCreateDateDesc(Long warehouseId, LocalDateTime modifyDate);
}
