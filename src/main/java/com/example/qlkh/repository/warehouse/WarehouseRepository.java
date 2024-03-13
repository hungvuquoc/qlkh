package com.example.qlkh.repository.warehouse;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.EbsWarehouseProjection;
import com.example.qlkh.dto.request.searchs.CardSearchDto;
import com.example.qlkh.dto.request.searchs.ReportSearchDto;
import com.example.qlkh.dto.request.searchs.WarehouseSearchDto;
import com.example.qlkh.dto.response.projection.CardProjectionDto;
import com.example.qlkh.dto.response.projection.ReportProjectionDto;
import com.example.qlkh.dto.response.projection.WarehouseProjectionRespDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.entity.warehouse.Warehouse;
import com.example.qlkh.entity.warehouse.WarehouseLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
//    @Query(
//            value = " select distinct wh.* from tbl_warehouse as wh " +
//                    " left join tbl_role rol on wh.id = rol.warehouse_id " +
//                    " left join tbl_user_role use_rol on rol.id = use_rol.role_id " +
//                    " left join tbl_user user on use_rol.user_id = user.id " +
//                    " where ( " +
//                    "   :#{#dto.keyword} is null " +
//                    "   or wh.name like %:#{#dto.keyword}% " +
//                    "   or wh.code like %:#{#dto.keyword}% " +
//                    "   or wh.address like %:#{#dto.keyword}% " +
//                    " ) " +
//                    " and (:username is null or user.username = :username) " +
//                    " order by wh.create_date desc " +
//                    " limit :#{#dto.limit} offset :#{#dto.offset} ",
//            nativeQuery = true
//    )
    @Query(
            value = " select distinct wh.* from tbl_warehouse as wh " +
                    " left join tbl_role rol on wh.id = rol.warehouse_id " +
                    " left join tbl_user_role use_rol on rol.id = use_rol.role_id " +
                    " left join tbl_user user on use_rol.user_id = user.id " +
                    " where ( " +
                    "   :#{#dto.keyword} is null " +
                    "   or wh.name like %:#{#dto.keyword}% " +
                    "   or wh.code like %:#{#dto.keyword}% " +
                    "   or wh.address like %:#{#dto.keyword}% " +
                    " ) " +
                    " and (:#{#dto.useAccount} is null or (:#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT}) or wh.id in (:#{#principal.warehouseIds})) " +
                    " order by wh.create_date desc " +
                    " limit :#{#dto.limit} offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<Warehouse> search(WarehouseSearchDto dto, EbsPrincipal principal);

    @Query(
            value = " select count(distinct wh.id) from tbl_warehouse as wh " +
                    " left join tbl_role rol on wh.id = rol.warehouse_id " +
                    " left join tbl_user_role use_rol on rol.id = use_rol.role_id " +
                    " left join tbl_user user on use_rol.user_id = user.id " +
                    " where ( " +
                    "   :#{#dto.keyword} is null " +
                    "   or wh.name like %:#{#dto.keyword}% " +
                    "   or wh.code like %:#{#dto.keyword}% " +
                    "   or wh.address like %:#{#dto.keyword}% " +
                    " ) " +
                    " and (:#{#dto.useAccount} is null or (:#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT}) or wh.id in (:#{#principal.warehouseIds})) ",
            nativeQuery = true
    )
    long count(WarehouseSearchDto dto, EbsPrincipal principal);

    @Query(
            value = " select right(wh.code, 3) from tbl_warehouse as wh " +
                    " order by right(wh.code, 3) desc " +
                    " limit 1",
            nativeQuery = true
    )
    String getMaxCode();

    @Query(
            value = " WITH location_detail_cte AS " +
                    " ( " +
                    "  SELECT " +
                    "   lcs.warehouse_area_id AS area_id, " +
                    "   lcs.id, " +
                    "   lcs.`name`, " +
                    "   lcs.`code`, " +
                    "   lcs.map_point, " +
                    "   count( lcs.id ) AS 'number_of_floor'  " +
                    "  FROM " +
                    "   tbl_warehouse_location AS lcs " +
                    "   JOIN tbl_warehouse_floor AS fl ON fl.warehouse_location_id = lcs.id  " +
                    "  GROUP BY " +
                    "   lcs.id " +
                    " ), " +
                    " location_object_cte AS ( " +
                    "  SELECT " +
                    "   lcs.area_id, " +
                    "   JSON_OBJECTAGG( " +
                    "    lcs.map_point, " +
                    "    JSON_OBJECT( " +
                    "     'id',  lcs.id, " +
                    "     'code',  lcs.`code`, " +
                    "     'name',  lcs.`name`, " +
                    "     'mapPoint',  lcs.map_point, " +
                    "     'numberOfFloor',  lcs.number_of_floor  " +
                    "    )) AS json_data  " +
                    "  FROM " +
                    "   location_detail_cte AS lcs  " +
                    "  GROUP BY " +
                    "   lcs.area_id " +
                    " ) " +
                    " SELECT  " +
                    "  wh.id," +
                    "  wh.code," +
                    "  wh.name, " +
                    "  wh.address, " +
                    "  wh.row_num as rowNumber, " +
                    "  wh.row_num as columnNumber, " +
                    "  wh.deleted, " +
                    "  CONCAT('[',  " +
                    "   GROUP_CONCAT(JSON_OBJECT( " +
                    "    'id', area.id, " +
                    "    'name', area.`name`, " +
                    "    'code', area.`code`, " +
                    "    'locations', lcs.json_data " +
                    "   ) SEPARATOR ',') " +
                    "  , ']') as jsonAreas " +
                    " FROM tbl_warehouse as wh " +
                    " JOIN tbl_warehouse_area as area on area.warehouse_id = wh.id " +
                    " JOIN location_object_cte as lcs on lcs.area_id = area.id " +
                    " where wh.id = :id " +
                    " GROUP BY wh.id  ",
            nativeQuery = true
    )
    WarehouseProjectionRespDto getWarehouseProjectionDtoById(Long id);

    @Query(
            value = " select * from tbl_warehouse_location as wl " +
                    " where wl.code rlike concat(:code, '/') and wl.deleted = false " +
                    " order by wl.code ",
            nativeQuery = true
    )
    List<WarehouseLocation> getLocationsByWarehouseCode(String code);

    @Query(
            value = " SELECT wh.id, wh.`code`, wh.`name`,  " +
                    " CONCAT( " +
                    "    '[', " +
                    "    GROUP_CONCAT( " +
                    "         distinct concat('\"', aut.`name`, '\"') order by concat('\"', aut.`name`, '\"')  SEPARATOR ','  " +
                    "    ), " +
                    "    ']' " +
                    " ) as jsonAuthorities " +
                    " FROM tbl_user_role as use_rol " +
                    " JOIN tbl_role as rol on rol.id = use_rol.role_id " +
                    " JOIN tbl_warehouse as wh on wh.id = rol.warehouse_id " +
                    " JOIN tbl_role_authority as rol_aut on rol_aut.role_id = rol.id " +
                    " JOIN tbl_authority as aut on aut.id = rol_aut.authority_id " +
                    " WHERE use_rol.user_id = :id " +
                    " GROUP BY wh.id, wh.`code`, wh.`name`",
            nativeQuery = true
    )
    List<EbsWarehouseProjection> getEbsWarehousesByUserId(Long id);

    @Query(
            value = " with start_stock_import as ( " +
                    "   select " +
                    "       pro.id as 'productId', " +
                    "       pro.code as 'productCode', " +
                    "       pro.name as 'productName', " +
                    "       imp_ite.consignment_number as 'consignmentNumber', " +
                    "       uni_rep.id as 'unitId', " +
                    "       uni_rep.name as 'unitName', " +
                    "       sum((imp_ite.quantity_target * uni_tar_con.ratio)/ uni_rep_con.ratio) as 'quantity', " +
                    "       sum(imp_ite.quantity_target * imp_ite.price) as 'cost' " +
                    "   from tbl_warehouse_import as imp " +
                    "   join tbl_warehouse_import_item imp_ite on imp.id = imp_ite.parent_id " +
                    "   join tbl_product as pro on imp_ite.product_id = pro.id " +
                    "   join tbl_product_unit_connect uni_tar_con on imp_ite.unit_target_id = uni_tar_con.id " +
                    "   join tbl_product_unit_connect uni_rep_con on uni_rep_con.product_id = pro.id and uni_rep_con.is_use_report is true " +
                    "   join tbl_product_unit uni_rep on uni_rep.id = uni_rep_con.unit_id " +
                    "   where imp.warehouse_id = :#{#dto.warehouseId} " +
                    "   and imp.status = :#{T(com.example.qlkh.constant.enums.WhTransactionStatus).FINISH.value}" +
                    "   and date(imp.input_date) < date(:#{#dto.startDate}) " +
                    "   and pro.type_id = :#{#dto.productTypeId} " +
                    "   group by pro.id, pro.code, pro.name, imp_ite.consignment_number, uni_rep.id, uni_rep.name " +
                    " )," +
                    " start_stock_export as ( " +
                    "   select " +
                    "       pro.id as 'productId', " +
                    "       pro.code as 'productCode', " +
                    "       pro.name as 'productName', " +
                    "       exp_ite.consignment_number as 'consignmentNumber', " +
                    "       uni_rep.id as 'unitId', " +
                    "       uni_rep.name as 'unitName', " +
                    "       - sum((exp_ite.quantity_target * uni_tar_con.ratio)/ uni_rep_con.ratio) as 'quantity', " +
                    "       - sum(exp_ite.quantity_target * exp_ite.price) as 'cost' " +
                    "   from tbl_warehouse_export as exp " +
                    "   join tbl_warehouse_export_item exp_ite on exp.id = exp_ite.parent_id " +
                    "   join tbl_product as pro on exp_ite.product_id = pro.id " +
                    "   join tbl_product_unit_connect uni_tar_con on exp_ite.unit_target_id = uni_tar_con.id " +
                    "   join tbl_product_unit_connect uni_rep_con on uni_rep_con.product_id = pro.id and uni_rep_con.is_use_report is true " +
                    "   join tbl_product_unit uni_rep on uni_rep.id = uni_rep_con.unit_id " +
                    "   where exp.warehouse_id = :#{#dto.warehouseId} " +
                    "   and exp.status = :#{T(com.example.qlkh.constant.enums.WhTransactionStatus).FINISH.value}" +
                    "   and date(exp.input_date) < date(:#{#dto.startDate}) " +
                    "   and pro.type_id = :#{#dto.productTypeId} " +
                    "   group by pro.id, pro.code, pro.name, exp_ite.consignment_number, uni_rep.id, uni_rep.name " +
                    " ), " +
                    " start_stock_transfer as ( " +
                    "   select " +
                    "       sum(if(tra.wh_import_id = :#{#dto.warehouseId}, tra_ite.quantity_target, - tra_ite.quantity_target) * tra_ite.price) as 'cost', " +
                    "       sum(if(tra.wh_import_id = :#{#dto.warehouseId}, tra_ite.quantity_target, - tra_ite.quantity_target) * uni_tar_con.ratio / uni_rep_con.ratio) as 'quantity', " +
                    "       pro.id as 'productId', " +
                    "       pro.code as 'productCode', " +
                    "       pro.name as 'productName', " +
                    "       tra_ite.consignment_number as 'consignmentNumber', " +
                    "       uni_rep.id as 'unitId', " +
                    "       uni_rep.name as 'unitName' " +
                    "   from tbl_warehouse_transfer as tra " +
                    "   join tbl_warehouse_transfer_item tra_ite on tra.id = tra_ite.parent_id " +
                    "   join tbl_product as pro on tra_ite.product_id = pro.id " +
                    "   join tbl_product_unit_connect uni_tar_con on tra_ite.unit_target_id = uni_tar_con.id " +
                    "   join tbl_product_unit_connect uni_rep_con on uni_rep_con.product_id = pro.id and uni_rep_con.is_use_report is true " +
                    "   join tbl_product_unit uni_rep on uni_rep.id = uni_rep_con.unit_id " +
                    "   where tra.wh_import_id <> tra.wh_export_id " +
                    "   and (tra.wh_import_id = :#{#dto.warehouseId} or tra.wh_export_id = :#{#dto.warehouseId}) " +
                    "   and tra.status = :#{T(com.example.qlkh.constant.enums.WhTransactionStatus).FINISH.value} " +
                    "   and date(tra.input_date) < date(:#{#dto.startDate}) " +
                    "   and pro.type_id = :#{#dto.productTypeId} " +
                    "   group by pro.id, pro.code, pro.name, tra_ite.consignment_number, uni_rep.id, uni_rep.name " +
                    " )," +
                    " start_stock as ( " +
                    "   select " +
                    "       combined.productId, " +
                    "       combined.consignmentNumber, " +
                    "       combined.productCode, " +
                    "       combined.productName, " +
                    "       combined.unitId, " +
                    "       combined.unitName, " +
                    "       sum(combined.quantity) as 'quantity', " +
                    "       sum(combined.cost) as 'cost' " +
                    "   from (" +
                    "       select * from start_stock_import " +
                    "       union " +
                    "       select * from start_stock_export " +
                    "       union " +
                    "       select * from start_stock_transfer " +
                    "   ) as combined " +
                    "   group by combined.productId, combined.consignmentNumber, combined.productCode, combined.productName, combined.unitId, combined.unitName " +
                    " ), " +
                    " in_import as ( " +
                    "   select " +
                    "       pro.id as 'productId', " +
                    "       pro.code as 'productCode', " +
                    "       pro.name as 'productName', " +
                    "       imp_ite.consignment_number as 'consignmentNumber', " +
                    "       uni_rep.id as 'unitId', " +
                    "       uni_rep.name as 'unitName', " +
                    "       sum((imp_ite.quantity_target * uni_tar_con.ratio)/ uni_rep_con.ratio) as 'quantity', " +
                    "       sum(imp_ite.quantity_target * imp_ite.price) as 'cost' " +
                    "   from tbl_warehouse_import as imp " +
                    "   join tbl_warehouse_import_item imp_ite on imp.id = imp_ite.parent_id " +
                    "   join tbl_product as pro on imp_ite.product_id = pro.id " +
                    "   join tbl_product_unit_connect uni_tar_con on imp_ite.unit_target_id = uni_tar_con.id " +
                    "   join tbl_product_unit_connect uni_rep_con on uni_rep_con.product_id = pro.id and uni_rep_con.is_use_report is true " +
                    "   join tbl_product_unit uni_rep on uni_rep.id = uni_rep_con.unit_id " +
                    "   where imp.warehouse_id = :#{#dto.warehouseId} " +
                    "   and imp.status = :#{T(com.example.qlkh.constant.enums.WhTransactionStatus).FINISH.value}" +
                    "   and date(imp.input_date) >= date(:#{#dto.startDate}) " +
                    "   and date(imp.input_date) <= date(:#{#dto.endDate}) " +
                    "   and pro.type_id = :#{#dto.productTypeId} " +
                    "   group by pro.id, pro.code, pro.name, imp_ite.consignment_number, uni_rep.id, uni_rep.name " +
                    " ), " +
                    " in_transfer as ( " +
                    "   select " +
                    "       pro.id as 'productId', " +
                    "       pro.code as 'productCode', " +
                    "       pro.name as 'productName', " +
                    "       tra_ite.consignment_number as 'consignmentNumber', " +
                    "       uni_rep.id as 'unitId', " +
                    "       uni_rep.name as 'unitName', " +
                    "       sum((tra_ite.quantity_target * uni_tar_con.ratio)/ uni_rep_con.ratio) as 'quantity', " +
                    "       sum(tra_ite.quantity_target * tra_ite.price) as 'cost' " +
                    "   from tbl_warehouse_transfer as tra " +
                    "   join tbl_warehouse_transfer_item tra_ite on tra.id = tra_ite.parent_id " +
                    "   join tbl_product as pro on tra_ite.product_id = pro.id " +
                    "   join tbl_product_unit_connect uni_tar_con on tra_ite.unit_target_id = uni_tar_con.id " +
                    "   join tbl_product_unit_connect uni_rep_con on uni_rep_con.product_id = pro.id and uni_rep_con.is_use_report is true " +
                    "   join tbl_product_unit uni_rep on uni_rep.id = uni_rep_con.unit_id " +
                    "   where tra.wh_import_id = :#{#dto.warehouseId} " +
                    "   and tra.wh_export_id <> :#{#dto.warehouseId} " +
                    "   and tra.status = :#{T(com.example.qlkh.constant.enums.WhTransactionStatus).FINISH.value}" +
                    "   and date(tra.input_date) >= date(:#{#dto.startDate}) " +
                    "   and date(tra.input_date) <= date(:#{#dto.endDate}) " +
                    "   and pro.type_id = :#{#dto.productTypeId} " +
                    "   group by pro.id, pro.code, pro.name, tra_ite.consignment_number, uni_rep.id, uni_rep.name " +
                    " )," +
                    " _in as ( " +
                    "   select " +
                    "       combined.productId, " +
                    "       combined.productCode, " +
                    "       combined.productName, " +
                    "       combined.consignmentNumber, " +
                    "       combined.unitId, " +
                    "       combined.unitName, " +
                    "       sum(combined.quantity) as 'quantity', " +
                    "       sum(combined.cost) as 'cost' " +
                    "   from (" +
                    "       select * from in_import " +
                    "       union " +
                    "       select * from in_transfer " +
                    "   ) as combined " +
                    "   group by combined.productId, combined.productCode, combined.productName, combined.consignmentNumber, combined.unitId, combined.unitName " +
                    " ), " +
                    " out_export as (" +
                    "   select " +
                    "       pro.id as 'productId', " +
                    "       pro.code as 'productCode', " +
                    "       pro.name as 'productName', " +
                    "       exp_ite.consignment_number as 'consignmentNumber', " +
                    "       uni_rep.id as 'unitId', " +
                    "       uni_rep.name as 'unitName', " +
                    "       sum((exp_ite.quantity_target * uni_tar_con.ratio)/ uni_rep_con.ratio) as 'quantity', " +
                    "       sum(exp_ite.quantity_target * exp_ite.price) as 'cost' " +
                    "   from tbl_warehouse_export as exp " +
                    "   join tbl_warehouse_export_item exp_ite on exp.id = exp_ite.parent_id " +
                    "   join tbl_product as pro on exp_ite.product_id = pro.id " +
                    "   join tbl_product_unit_connect uni_tar_con on exp_ite.unit_target_id = uni_tar_con.id " +
                    "   join tbl_product_unit_connect uni_rep_con on uni_rep_con.product_id = pro.id and uni_rep_con.is_use_report is true " +
                    "   join tbl_product_unit uni_rep on uni_rep.id = uni_rep_con.unit_id " +
                    "   where exp.warehouse_id = :#{#dto.warehouseId} " +
                    "   and exp.status = :#{T(com.example.qlkh.constant.enums.WhTransactionStatus).FINISH.value}" +
                    "   and date(exp.input_date) >= date(:#{#dto.startDate}) " +
                    "   and date(exp.input_date) <= date(:#{#dto.endDate}) " +
                    "   and pro.type_id = :#{#dto.productTypeId} " +
                    "   group by pro.id, pro.code, pro.name, exp_ite.consignment_number, uni_rep.id, uni_rep.name " +
                    " ), " +
                    " out_transfer as ( " +
                    "   select " +
                    "       pro.id as 'productId', " +
                    "       pro.code as 'productCode', " +
                    "       pro.name as 'productName', " +
                    "       tra_ite.consignment_number as 'consignmentNumber', " +
                    "       uni_rep.id as 'unitId', " +
                    "       uni_rep.name as 'unitName', " +
                    "       sum((tra_ite.quantity_target * uni_tar_con.ratio)/ uni_rep_con.ratio) as 'quantity', " +
                    "       sum(tra_ite.quantity_target * tra_ite.price) as 'cost' " +
                    "   from tbl_warehouse_transfer as tra " +
                    "   join tbl_warehouse_transfer_item tra_ite on tra.id = tra_ite.parent_id " +
                    "   join tbl_product as pro on tra_ite.product_id = pro.id " +
                    "   join tbl_product_unit_connect uni_tar_con on tra_ite.unit_target_id = uni_tar_con.id " +
                    "   join tbl_product_unit_connect uni_rep_con on uni_rep_con.product_id = pro.id and uni_rep_con.is_use_report is true " +
                    "   join tbl_product_unit uni_rep on uni_rep.id = uni_rep_con.unit_id " +
                    "   where tra.wh_import_id <> :#{#dto.warehouseId} " +
                    "   and tra.wh_export_id = :#{#dto.warehouseId} " +
                    "   and tra.status = :#{T(com.example.qlkh.constant.enums.WhTransactionStatus).FINISH.value}" +
                    "   and date(tra.input_date) >= date(:#{#dto.startDate}) " +
                    "   and date(tra.input_date) <= date(:#{#dto.endDate}) " +
                    "   and pro.type_id = :#{#dto.productTypeId} " +
                    "   group by pro.id, pro.code, pro.name, tra_ite.consignment_number, uni_rep.id, uni_rep.name " +
                    " ), " +
                    " _out as ( " +
                    "   select " +
                    "       combined.productId, " +
                    "       combined.productCode, " +
                    "       combined.productName, " +
                    "       combined.consignmentNumber, " +
                    "       combined.unitId, " +
                    "       combined.unitName, " +
                    "       sum(combined.quantity) as 'quantity', " +
                    "       sum(combined.cost) as 'cost' " +
                    "   from (" +
                    "       select * from out_export " +
                    "       union " +
                    "       select * from out_transfer " +
                    "   ) as combined " +
                    "   group by combined.productId, combined.productCode, combined.productName, combined.consignmentNumber, combined.unitId, combined.unitName " +
                    " ), " +
                    " product_consignment_unit as ( " +
                    "   select " +
                    "       combined.productId, " +
                    "       combined.productCode, " +
                    "       combined.productName, " +
                    "       combined.consignmentNumber, " +
                    "       combined.unitId, " +
                    "       combined.unitName " +
                    "   from (" +
                    "       select " +
                    "           start_stock.productId, start_stock.productCode, start_stock.productName, start_stock.consignmentNumber, start_stock.unitId, start_stock.unitName " +
                    "       from start_stock " +
                    "       union " +
                    "       select " +
                    "          _in.productId, _in.productCode, _in.productName, _in.consignmentNumber, _in.unitId, _in.unitName " +
                    "       from _in " +
                    "       union " +
                    "       select " +
                    "           _out.productId, _out.productCode, _out.productName, _out.consignmentNumber, _out.unitId, _out.unitName" +
                    "       from _out " +
                    "   ) as combined " +
                    "   group by combined.productId, combined.productCode, combined.productName, combined.consignmentNumber, combined.unitId, combined.unitName " +
                    " ) " +
                    " select " +
                    "   pro_con_uni.productId, " +
                    "   pro_con_uni.productCode, " +
                    "   pro_con_uni.productName, " +
                    "   pro_con_uni.consignmentNumber, " +
                    "   pro_con_uni.unitId, " +
                    "   pro_con_uni.unitName, " +
                    "   coalesce(start_stock.quantity, 0) as 'startStockQuantity', " +
                    "   coalesce(start_stock.cost, 0) as 'startStockCost', " +
                    "   coalesce(_in.quantity, 0) as 'inQuantity', " +
                    "   coalesce(_in.cost, 0) as 'inCost', " +
                    "   coalesce(_out.quantity, 0) as 'outQuantity', " +
                    "   coalesce(_out.cost, 0) as 'outCost', " +
                    "   (coalesce(start_stock.quantity, 0) + coalesce(_in.quantity, 0) - coalesce(_out.quantity, 0)) as 'endStockQuantity', " +
                    "   (coalesce(start_stock.cost, 0) + coalesce(_in.cost, 0) - coalesce(_out.cost, 0)) as 'endStockCost' " +
                    " from product_consignment_unit as pro_con_uni " +
                    " left join start_stock on start_stock.productId = pro_con_uni.productId and start_stock.consignmentNumber = pro_con_uni.consignmentNumber and start_stock.unitId = pro_con_uni.unitId " +
                    " left join _in on _in.productId = pro_con_uni.productId and _in.consignmentNumber = pro_con_uni.consignmentNumber and _in.unitId = pro_con_uni.unitId " +
                    " left join _out on _out.productId = pro_con_uni.productId and _out.consignmentNumber = pro_con_uni.consignmentNumber and _out.unitId = pro_con_uni.unitId ",
            nativeQuery = true
    )
    List<ReportProjectionDto> getReport(@Param("dto") ReportSearchDto dto);

    @Query(
            value = " with _in as (" +
                    "   select " +
                    "       :#{T(com.example.qlkh.constant.enums.CardType).IMPORT.value} as 'type', " +
                    "       if(imp.modify_date is not null, imp.modify_date, imp.create_date) as 'createDate', " +
                    "       imp.code, " +
                    "       :#{T(com.example.qlkh.constant.enums.CardType).IMPORT.note} as 'note', " +
                    "       imp.input_date as 'inputDate', " +
                    "       sum((imp_ite.quantity_target * uni_tar_con.ratio)/ uni_rep_con.ratio) as 'quantity' " +
                    "   from tbl_warehouse_import_item as imp_ite " +
                    "   join tbl_warehouse_import as imp on imp_ite.parent_id = imp.id " +
                    "   join tbl_product as pro on imp_ite.product_id = pro.id " +
                    "   join tbl_product_unit_connect uni_tar_con on imp_ite.unit_target_id = uni_tar_con.id " +
                    "   join tbl_product_unit_connect uni_rep_con on uni_rep_con.product_id = pro.id and uni_rep_con.is_use_report is true " +
                    "   where imp.warehouse_id = :#{#dto.warehouseId} " +
                    "   and imp.status = :#{T(com.example.qlkh.constant.enums.WhTransactionStatus).FINISH.value} " +
                    "   and imp_ite.product_id = :#{#dto.productId} " +
                    "   group by if(imp.modify_date is not null, imp.modify_date, imp.create_date), imp.code, imp.input_date " +
                    " ), " +
                    " _out as ( " +
                    "   select " +
                    "       :#{T(com.example.qlkh.constant.enums.CardType).EXPORT.value} as 'type', " +
                    "       if(exp.modify_date is not null, exp.modify_date, exp.create_date) as 'createDate', " +
                    "       exp.code, " +
                    "       :#{T(com.example.qlkh.constant.enums.CardType).EXPORT.note} as 'note', " +
                    "       exp.input_date as 'inputDate', " +
                    "       sum((exp_ite.quantity_target * uni_tar_con.ratio)/ uni_rep_con.ratio) as 'quantity' " +
                    "   from tbl_warehouse_export_item as exp_ite " +
                    "   join tbl_warehouse_export as exp on exp_ite.parent_id = exp.id " +
                    "   join tbl_product as pro on exp_ite.product_id = pro.id " +
                    "   join tbl_product_unit_connect uni_tar_con on exp_ite.unit_target_id = uni_tar_con.id " +
                    "   join tbl_product_unit_connect uni_rep_con on uni_rep_con.product_id = pro.id and uni_rep_con.is_use_report is true " +
                    "   where exp.warehouse_id = :#{#dto.warehouseId} " +
                    "   and exp.status = :#{T(com.example.qlkh.constant.enums.WhTransactionStatus).FINISH.value} " +
                    "   and exp_ite.product_id = :#{#dto.productId} " +
                    "   group by if(exp.modify_date is not null, exp.modify_date, exp.create_date), exp.code, exp.input_date " +
                    " ), " +
                    " _transfer as ( " +
                    "   select " +
                    "       if (tra.wh_import_id = :#{#dto.warehouseId}, " +
                    "           :#{T(com.example.qlkh.constant.enums.CardType).IMPORT.value}, " +
                    "           :#{T(com.example.qlkh.constant.enums.CardType).EXPORT.value} " +
                    "          ) as 'type', " +
                    "       if(tra.modify_date is not null, tra.modify_date, tra.create_date) as 'createDate', " +
                    "       tra.code, " +
                    "       :#{T(com.example.qlkh.constant.enums.CardType).TRANSFER.note} as 'note', " +
                    "       tra.input_date as 'inputDate', " +
                    "       sum((tra_ite.quantity_target * uni_tar_con.ratio)/ uni_rep_con.ratio) as 'quantity' " +
                    "   from tbl_warehouse_transfer_item as tra_ite " +
                    "   join tbl_warehouse_transfer as tra on tra_ite.parent_id = tra.id " +
                    "   join tbl_product as pro on tra_ite.product_id = pro.id " +
                    "   join tbl_product_unit_connect uni_tar_con on tra_ite.unit_target_id = uni_tar_con.id " +
                    "   join tbl_product_unit_connect uni_rep_con on uni_rep_con.product_id = pro.id and uni_rep_con.is_use_report is true " +
                    "   where tra.wh_export_id <> tra.wh_import_id " +
                    "   and (tra.wh_import_id = :#{#dto.warehouseId} or tra.wh_export_id = :#{#dto.warehouseId}) " +
                    "   and tra.status = :#{T(com.example.qlkh.constant.enums.WhTransactionStatus).FINISH.value} " +
                    "   and tra_ite.product_id = :#{#dto.productId} " +
                    "   group by " +
                    "       if (tra.wh_import_id = :#{#dto.warehouseId}, " +
                    "           :#{T(com.example.qlkh.constant.enums.CardType).IMPORT.value}, " +
                    "           :#{T(com.example.qlkh.constant.enums.CardType).EXPORT.value}), " +
                    "       if(tra.modify_date is not null, tra.modify_date, tra.create_date), " +
                    "       tra.code, " +
                    "       tra.input_date " +
                    " ), " +
                    " item as ( " +
                    "   select type, createDate, code, note, inputDate, quantity from _in " +
                    "   union " +
                    "   select type, createDate, code, note, inputDate, quantity from _out " +
                    "   union " +
                    "   select type, createDate, code, note, inputDate, quantity from _transfer " +
                    " )" +
                    " select " +
                    "   ite_1.type, " +
                    "   ite_1.createDate as 'createDate', " +
                    "   ite_1.code, " +
                    "   ite_1.note, " +
                    "   ite_1.inputDate as 'inputDate', " +
                    "   ite_1.quantity, " +
                    "   sum(if (ite_2.type = :#{T(com.example.qlkh.constant.enums.CardType).IMPORT.value}, ite_2.quantity, - ite_2.quantity)) as 'stockQuantity' " +
                    " from item as ite_1 " +
                    " join item as ite_2 on ite_1.createDate >= ite_2.createDate " +
                    " where date(ite_1.createDate) >= date(:#{#dto.startDate}) " +
                    " and date(ite_1.createDate) <= date(:#{#dto.endDate}) " +
                    " group by ite_1.type, ite_1.createDate, ite_1.code, ite_1.note, ite_1.inputDate, ite_1.quantity " +
                    " order by ite_1.createDate " +
                    " limit :#{#dto.limit} " +
                    " offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<CardProjectionDto> getCard(@Param("dto") CardSearchDto dto);

    @Query(
            value = " with _in as (" +
                    "   select " +
                    "       :#{T(com.example.qlkh.constant.enums.CardType).IMPORT.value} as 'type', " +
                    "       if(imp.modify_date is not null, imp.modify_date, imp.create_date) as 'createDate', " +
                    "       imp.code, " +
                    "       :#{T(com.example.qlkh.constant.enums.CardType).IMPORT.note} as 'note', " +
                    "       imp.input_date as 'inputDate', " +
                    "       sum((imp_ite.quantity_target * uni_tar_con.ratio)/ uni_rep_con.ratio) as 'quantity' " +
                    "   from tbl_warehouse_import_item as imp_ite " +
                    "   join tbl_warehouse_import as imp on imp_ite.parent_id = imp.id " +
                    "   join tbl_product as pro on imp_ite.product_id = pro.id " +
                    "   join tbl_product_unit_connect uni_tar_con on imp_ite.unit_target_id = uni_tar_con.id " +
                    "   join tbl_product_unit_connect uni_rep_con on uni_rep_con.product_id = pro.id and uni_rep_con.is_use_report is true " +
                    "   where imp.warehouse_id = :#{#dto.warehouseId} " +
                    "   and imp.status = :#{T(com.example.qlkh.constant.enums.WhTransactionStatus).FINISH.value} " +
                    "   and imp_ite.product_id = :#{#dto.productId} " +
                    "   group by if(imp.modify_date is not null, imp.modify_date, imp.create_date), imp.code, imp.input_date " +
                    " ), " +
                    " _out as ( " +
                    "   select " +
                    "       :#{T(com.example.qlkh.constant.enums.CardType).EXPORT.value} as 'type', " +
                    "       if(exp.modify_date is not null, exp.modify_date, exp.create_date) as 'createDate', " +
                    "       exp.code, " +
                    "       :#{T(com.example.qlkh.constant.enums.CardType).EXPORT.note} as 'note', " +
                    "       exp.input_date as 'inputDate', " +
                    "       sum((exp_ite.quantity_target * uni_tar_con.ratio)/ uni_rep_con.ratio) as 'quantity' " +
                    "   from tbl_warehouse_export_item as exp_ite " +
                    "   join tbl_warehouse_export as exp on exp_ite.parent_id = exp.id " +
                    "   join tbl_product as pro on exp_ite.product_id = pro.id " +
                    "   join tbl_product_unit_connect uni_tar_con on exp_ite.unit_target_id = uni_tar_con.id " +
                    "   join tbl_product_unit_connect uni_rep_con on uni_rep_con.product_id = pro.id and uni_rep_con.is_use_report is true " +
                    "   where exp.warehouse_id = :#{#dto.warehouseId} " +
                    "   and exp.status = :#{T(com.example.qlkh.constant.enums.WhTransactionStatus).FINISH.value} " +
                    "   and exp_ite.product_id = :#{#dto.productId} " +
                    "   group by if(exp.modify_date is not null, exp.modify_date, exp.create_date), exp.code, exp.input_date " +
                    " ), " +
                    " _transfer as ( " +
                    "   select " +
                    "       if (tra.wh_import_id = :#{#dto.warehouseId}, " +
                    "           :#{T(com.example.qlkh.constant.enums.CardType).IMPORT.value}, " +
                    "           :#{T(com.example.qlkh.constant.enums.CardType).EXPORT.value} " +
                    "          ) as 'type', " +
                    "       if(tra.modify_date is not null, tra.modify_date, tra.create_date) as 'createDate', " +
                    "       tra.code, " +
                    "       :#{T(com.example.qlkh.constant.enums.CardType).TRANSFER.note} as 'note', " +
                    "       tra.input_date as 'inputDate', " +
                    "       sum((tra_ite.quantity_target * uni_tar_con.ratio)/ uni_rep_con.ratio) as 'quantity' " +
                    "   from tbl_warehouse_transfer_item as tra_ite " +
                    "   join tbl_warehouse_transfer as tra on tra_ite.parent_id = tra.id " +
                    "   join tbl_product as pro on tra_ite.product_id = pro.id " +
                    "   join tbl_product_unit_connect uni_tar_con on tra_ite.unit_target_id = uni_tar_con.id " +
                    "   join tbl_product_unit_connect uni_rep_con on uni_rep_con.product_id = pro.id and uni_rep_con.is_use_report is true " +
                    "   where tra.wh_export_id <> tra.wh_import_id " +
                    "   and (tra.wh_import_id = :#{#dto.warehouseId} or tra.wh_export_id = :#{#dto.warehouseId}) " +
                    "   and tra.status = :#{T(com.example.qlkh.constant.enums.WhTransactionStatus).FINISH.value} " +
                    "   and tra_ite.product_id = :#{#dto.productId} " +
                    "   group by " +
                    "       if (tra.wh_import_id = :#{#dto.warehouseId}, " +
                    "           :#{T(com.example.qlkh.constant.enums.CardType).IMPORT.value}, " +
                    "           :#{T(com.example.qlkh.constant.enums.CardType).EXPORT.value}), " +
                    "       if(tra.modify_date is not null, tra.modify_date, tra.create_date), " +
                    "       tra.code, " +
                    "       tra.input_date " +
                    " ), " +
                    " item as ( " +
                    "   select type, createDate, code, note, inputDate, quantity from _in " +
                    "   union " +
                    "   select type, createDate, code, note, inputDate, quantity from _out " +
                    "   union " +
                    "   select type, createDate, code, note, inputDate, quantity from _transfer " +
                    " )" +
                    " select count(1) from item " +
                    " where date(item.createDate) >= date(:#{#dto.startDate}) " +
                    " and date(item.createDate) <= date(:#{#dto.endDate}) ",
            nativeQuery = true
    )
    long count(@Param("dto") CardSearchDto dto);
}
