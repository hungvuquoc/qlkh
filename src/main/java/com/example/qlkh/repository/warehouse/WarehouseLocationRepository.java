package com.example.qlkh.repository.warehouse;

import com.example.qlkh.dto.request.searchs.StockSearchDto;
import com.example.qlkh.entity.warehouse.WarehouseLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WarehouseLocationRepository extends JpaRepository<WarehouseLocation, Long> {
    @Query(
            value = " select * from tbl_warehouse_location as wl " +
                    " where wl.code rlike concat(:code, '/') and wl.deleted = false " +
                    " order by wl.code ",
            nativeQuery = true
    )
    List<WarehouseLocation> getLocationsByWarehouseCode(String code);

    @Modifying
    // nháp
    @Query(
            value = " delete from tbl_warehouse_location " +
                    " where id in :ids ",
            nativeQuery = true
    )
    void deleteByIds(List<Long> ids);

    @Query(
            value = " with warehouse as " +
                    " ( " +
                    "   select concat(wh.code, '/') from tbl_warehouse as wh " +
                    "   where wh.id = :warehouseId " +
                    " ) " +
                    " select distinct loc.map_point from tbl_warehouse_location as loc " +
                    " join tbl_warehouse_floor flo on loc.id = flo.warehouse_location_id and flo.deleted = false " +
                    " join tbl_product_floor pro_flo on flo.id = pro_flo.floor_id and pro_flo.quantity_target > 0 " +
                    " where loc.code rlike (select * from warehouse) ",
            nativeQuery = true
    )
    List<String> getMapPointHasProductByWarehouseId(Long warehouseId);

    @Query(
            value = " with warehouse as " +
                    " ( " +
                    "   select concat(wh.code, '/') from tbl_warehouse as wh " +
                    "   where wh.id = :#{#dto.warehouseId}" +
                    " ) " +
                    " select distinct loc.map_point from tbl_warehouse_location as loc " +
                    " join tbl_warehouse_floor flo on flo.warehouse_location_id = loc.id " +
                    " join tbl_product_floor as pro_flo on pro_flo.floor_id = flo.id " +
                    " where pro_flo.quantity_target > 0 " +
                    " and pro_flo.product_id = :#{#dto.productId} " +
                    " and pro_flo.product_detail_id = :#{#dto.productDetailId} " +
                    " and pro_flo.unit_target_id = :#{#dto.unitId} " +
                    " and ( " +
                    "       pro_flo.consignment_number = :#{#dto.consignmentNumber} " +
                    "       or (pro_flo.consignment_number = '' and :#{#dto.consignmentNumber} = 'Không có lô') " +
                    "     ) " +
                    " and flo.deleted = false " +
                    " and flo.code rlike (select * from warehouse) ",
            nativeQuery = true
    )
    List<String> getMapPointByProductFloorId(StockSearchDto dto);

    @Query(
            value = " select right(loc.code, 3) from tbl_warehouse_location as loc " +
                    " where loc.deleted = false " +
                    " and loc.code rlike (concat(:areaCode, '/')) and loc.id not it :locationIds " +
                    " order by right(loc.code, 3) desc " +
                    " limit 1 ",
            nativeQuery = true
    )
    String getMaxCode(String areaCode, List<Long> locationIds);

    @Query(
            value = " select right(loc.code, 3) from tbl_warehouse_location as loc " +
                    " where loc.deleted = false " +
                    " and loc.code rlike (concat(:areaCode, '/')) " +
                    " order by right(loc.code, 3) desc " +
                    " limit 1 ",
            nativeQuery = true
    )
    String getMaxCode(String areaCode);

    @Query(
            value = " with warehouse as " +
                    " ( " +
                    "    select concat(wh.`code`, '/') from tbl_warehouse as wh  " +
                    "    where wh.id = :warehouseId " +
                    " ), " +
                    " location_cur as  " +
                    " ( " +
                    "    select loc.* from tbl_warehouse_location as loc " +
                    "    where loc.deleted = false  " +
                    "    and (:modifyDate is null or loc.create_date <= :modifyDate)  " +
                    "    and loc.`code` rlike (select * from warehouse) " +
                    " ), " +
                    " location_del as  " +
                    " ( " +
                    "    select  " +
                    "    loc.*,  " +
                    "    row_number() over(partition by loc.map_point order by loc.create_date asc) as row_num from tbl_warehouse_location as loc " +
                    "    where loc.deleted = true " +
                    "    and :modifyDate is not null " +
                    "    and loc.modify_date >= :modifyDate " +
                    "    and loc.`code` rlike (select * from warehouse) " +
                    " ) " +
                    " select location.*  " +
                    " from " +
                    " ( " +
                    "    select  " +
                    "    loc_cur.`id`,  " +
                    "    loc_cur.`create_by`,  " +
                    "    loc_cur.`create_date`,  " +
                    "    loc_cur.`modify_by`,  " +
                    "    loc_cur.`modify_date`,  " +
                    "    loc_cur.`name`, `code`,  " +
                    "    loc_cur.`map_point`,  " +
                    "    loc_cur.`warehouse_area_id`,  " +
                    "    loc_cur.`deleted` " +
                    "    from location_cur as loc_cur " +
                    "    union " +
                    "    select  " +
                    "    loc_del.`id`,  " +
                    "    loc_del.`create_by`,  " +
                    "    loc_del.`create_date`,  " +
                    "    loc_del.`modify_by`,  " +
                    "    loc_del.`modify_date`,  " +
                    "    loc_del.`name`, `code`,  " +
                    "    loc_del.`map_point`,  " +
                    "    loc_del.`warehouse_area_id`,  " +
                    "    loc_del.`deleted` " +
                    "    from location_del as loc_del " +
                    "    where loc_del.row_num = 1 " +
                    " ) as location ",
            nativeQuery = true
    )
    List<WarehouseLocation> getLocationsWithWarehouseIdAndModifyDate(Long warehouseId, LocalDateTime modifyDate);

    @Modifying
    @Query(
            value = " update tbl_warehouse_location as loc " +
                    " set loc.deleted = true, modify_by = :#{T(com.example.qlkh.utils.EbsSecurityUtils).username}, modify_date = now() " +
                    " where loc.id in :ids ",
            nativeQuery = true
    )
    void updateDeletedByIds(List<Long> ids);

    @Modifying
    @Query(
            value = " update tbl_warehouse_location as loc " +
                    " set loc.deleted = true, modify_by = :#{T(com.example.qlkh.utils.EbsSecurityUtils).username}, modify_date = now() " +
                    " where loc.warehouse_area_id in :areaIds ",
            nativeQuery = true
    )
    void updateDeletedByAreaIds(List<Long> areaIds);
}
