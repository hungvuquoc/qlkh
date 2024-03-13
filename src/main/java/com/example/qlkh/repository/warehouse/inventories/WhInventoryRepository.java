package com.example.qlkh.repository.warehouse.inventories;

import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.entity.warehouse.inventories.WhInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhInventoryRepository extends JpaRepository<WhInventory, Long> {
    @Query(
            value = " select inv.* from tbl_warehouse_inventory as inv " +
                    " join tbl_warehouse as war on inv.warehouse_id = war.id " +
                    " where ( " +
                    "   :#{#dto.keyword} is null " +
                    "   or inv.code like %:#{#dto.keyword}% " +
                    "   or war.name like %:#{#dto.keyword}% " +
                    " ) " +
                    " order by inv.create_date desc " +
                    " limit :#{#dto.limit} " +
                    " offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<WhInventory> search(SearchDto dto);

    @Query(
            value = " select count(1) from tbl_warehouse_inventory as inv " +
                    " join tbl_warehouse as war on inv.warehouse_id = war.id " +
                    " where ( " +
                    "   :#{#dto.keyword} is null " +
                    "   or inv.code like %:#{#dto.keyword}% " +
                    "   or war.name like %:#{#dto.keyword}% " +
                    " ) ",
            nativeQuery = true
    )
    long count(SearchDto dto);

    @Query(
            value = " SELECT right(wi.code, 5) FROM tbl_warehouse_inventory AS wi " +
                    " ORDER BY right(wi.code, 12) DESC " +
                    " LIMIT 1",
            nativeQuery = true
    )
    String getMaxCode();
}
