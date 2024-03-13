package com.example.qlkh.repository.products;

import com.example.qlkh.dto.request.searchs.ProductFloorSearchDto;
import com.example.qlkh.dto.request.searchs.StockSearchDto;
import com.example.qlkh.dto.response.projection.ProductFloorDetailProjectionDto;
import com.example.qlkh.dto.response.projection.ProductFloorProjectionDto;
import com.example.qlkh.entity.product.ProductFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFloorRepository extends JpaRepository<ProductFloor, Long> {
    @Query(
            value = " select * from tbl_product_floor as pf " +
                    " where (:#{#dto.floorId} is null or pf.floor_id = :#{#dto.floorId}) " +
                    " and (:#{#dto.productId} is null or pf.product_id = :#{#dto.productId}) " +
                    " and (" +
                    "       :#{#dto.consignment} is null " +
                    "       or pf.consignment_number = :#{#dto.consignment}" +
                    "     ) " +
                    " and (:#{#dto.inputDate} is null or date(pf.input_date) = date(:#{#dto.inputDate})) " +
                    " and (:#{#dto.unitTargetId} is null or pf.unit_target_id = :#{#dto.unitTargetId}) " +
                    " and (:#{#dto.productDetailId} is null or pf.product_detail_id = :#{#dto.productDetailId}) ",
            nativeQuery = true
    )
    ProductFloor search(ProductFloorSearchDto dto);

    @Query(
            value = " select sum(pf.quantity_target) from tbl_product_floor as pf " +
                    " where (:#{#dto.floorId} is null or pf.floor_id = :#{#dto.floorId}) " +
                    " and (:#{#dto.productId} is null or pf.product_id = :#{#dto.productId}) " +
                    " and (" +
                    "       :#{#dto.consignment} is null " +
                    "       or pf.consignment_number = :#{#dto.consignment}" +
                    "     ) " +
                    " and (:#{#dto.inputDate} is null or date(pf.input_date) = date(:#{#dto.inputDate})) " +
                    " and (:#{#dto.unitTargetId} is null or pf.unit_target_id = :#{#dto.unitTargetId}) " +
                    " and (:#{#dto.productDetailId} is null or pf.product_detail_id = :#{#dto.productDetailId}) ",
            nativeQuery = true
    )
    Double getProductQuantity(ProductFloorSearchDto dto);

    @Query(
            value = " with warehouse as " +
                    " ( " +
                    "   select concat(wh.code, '/') from tbl_warehouse as wh " +
                    "   where wh.id = :#{#dto.warehouseId} " +
                    " ) " +
                    " select " +
                    " distinct coalesce(nullif(pro_flo.consignment_number, ''), 'Không có lô' ) " +
                    " from tbl_product_floor as pro_flo " +
                    " join tbl_warehouse_floor flo on pro_flo.floor_id = flo.id " +
                    " where pro_flo.quantity_target > 0 " +
                    " and pro_flo.product_id = :#{#dto.productId} " +
                    " and pro_flo.product_detail_id = :#{#dto.productDetailId} " +
                    " and pro_flo.unit_target_id = :#{#dto.unitId} " +
                    " and flo.deleted = false " +
                    " and flo.code rlike (select * from warehouse) ",
            nativeQuery = true
    )
    List<String> getConsignmentInStock(StockSearchDto dto);

    @Query(
            value = " with warehouse as " +
                    " ( " +
                    "   select concat(wh.code, '/') from tbl_warehouse as wh " +
                    "   where wh.id = :#{#dto.warehouseId}" +
                    " ) " +
                    " select " +
                    " pro_flo.id as 'id', " +
                    " loc.map_point as 'mapPoint', " +
                    " pro_flo.floor_id as 'floorId', " +
                    " flo.name as 'floorName', " +
                    " pro_flo.input_date as 'inputDate', " +
                    " pro_flo.quantity_target as 'quantityTarget', " +
                    " pro_flo.quantity_source as 'quantitySource' " +
                    " from tbl_product_floor as pro_flo " +
                    " join tbl_warehouse_floor flo on pro_flo.floor_id = flo.id " +
                    " join tbl_warehouse_location loc on flo.warehouse_location_id = loc.id " +
                    " where pro_flo.quantity_target > 0 " +
                    " and pro_flo.product_id = :#{#dto.productId} " +
                    " and pro_flo.product_detail_id = :#{#dto.productDetailId} " +
                    " and pro_flo.unit_target_id = :#{#dto.unitId} " +
                    " and flo.warehouse_location_id = :#{#dto.locationId} " +
                    " and ( " +
                    "       pro_flo.consignment_number = :#{#dto.consignmentNumber} " +
                    "       or (pro_flo.consignment_number = '' and :#{#dto.consignmentNumber} = 'Không có lô') " +
                    "     ) " +
                    " and flo.deleted = false " +
                    " and flo.code rlike (select * from warehouse) ",
            nativeQuery = true
    )
    List<ProductFloorProjectionDto> getInStock(StockSearchDto dto);

    @Query(
            value = " select if( " +
                    "   exists(" +
                    "       select 1 from tbl_product_floor as pro_flo " +
                    "       join tbl_warehouse_floor flo on pro_flo.floor_id = flo.id " +
                    "       where pro_flo.quantity_target > 0 " +
                    "       and flo.warehouse_location_id in :locationIds " +
                    "   ) " +
                    " , 'true', 'false') ",
            nativeQuery = true
    )
    boolean isLocationUsing(List<Long> locationIds);

    @Query(
            value = " select if( " +
                    "   exists(" +
                    "       select 1 from tbl_product_floor as pro_flo " +
                    "       join tbl_warehouse_floor flo on pro_flo.floor_id = flo.id " +
                    "       join tbl_warehouse_location as loc on flo.warehouse_location_id = loc.id " +
                    "       where pro_flo.quantity_target > 0 " +
                    "       and loc.warehouse_area_id in :areaIds " +
                    "   ) " +
                    " , 'true', 'false') ",
            nativeQuery = true
    )
    boolean isAreaUsing(List<Long> areaIds);

    @Query(
            value = " with location_path as " +
                    " ( " +
                    "   select concat(loc.code, '/') as 'path' from tbl_warehouse_location as loc " +
                    "   where loc.id = :#{#dto.locationId} " +
                    " ) " +
                    " select " +
                    "  flo.name as 'floorName', " +
                    "  pro.name as 'productName', " +
                    "  pro_det.price as 'detailPrice', " +
                    "  pro_det.quality as 'detailQuantity', " +
                    "  pro_det.size as 'detailSize', " +
                    "  pro_uni.name as 'unitName', " +
                    "  pro_uni_con.ratio as 'unitRatio'," +
                    "  pro_uni_con.is_default as 'unitDefault'," +
                    "  pro_uni_con.is_use_report as 'unitReport', " +
                    "  pro_flo.input_date as 'inputDate'," +
                    "  pro_flo.quantity_target as 'quantity' " +
                    " from tbl_product_floor as pro_flo " +
                    " join tbl_warehouse_floor flo on pro_flo.floor_id = flo.id " +
                    " join tbl_product as pro on pro.id = pro_flo.product_id " +
                    " join tbl_product_detail as pro_det on pro_det.id = pro_flo.product_detail_id " +
                    " join tbl_product_unit_connect as pro_uni_con on pro_uni_con.id = pro_flo.unit_target_id " +
                    " join tbl_product_unit as pro_uni on pro_uni.id = pro_uni_con.unit_id " +
                    " where flo.code rlike(select * from location_path) " +
                    " and pro_flo.quantity_target > 0 " +
                    " order by flo.name, pro.name, pro_flo.input_date " +
                    " limit :#{#dto.limit} " +
                    " offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<ProductFloorDetailProjectionDto> searchProductFloorDetail(ProductFloorSearchDto dto);

    @Query(
            value = " with location_path as " +
                    " ( " +
                    "   select concat(loc.code, '/') as 'path' from tbl_warehouse_location as loc " +
                    "   where loc.id = :#{#dto.locationId} " +
                    " ) " +
                    " select count(1) " +
                    " from tbl_product_floor as pro_flo " +
                    " join tbl_warehouse_floor flo on pro_flo.floor_id = flo.id " +
                    " join tbl_product as pro on pro.id = pro_flo.product_id " +
                    " join tbl_product_detail as pro_det on pro_det.id = pro_flo.product_detail_id " +
                    " join tbl_product_unit_connect as pro_uni_con on pro_uni_con.id = pro_flo.unit_target_id " +
                    " join tbl_product_unit as pro_uni on pro_uni.id = pro_uni_con.unit_id " +
                    " where flo.code rlike(select * from location_path) " +
                    " and pro_flo.quantity_target > 0 ",
            nativeQuery = true
    )
    long countProductFloorDetail(ProductFloorSearchDto dto);
}
