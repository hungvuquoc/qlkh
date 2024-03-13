package com.example.qlkh.repository.warehouse.exports;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.request.searchs.WhExportSearchReqDto;
import com.example.qlkh.entity.warehouse.exports.WhExport;
import com.example.qlkh.entity.warehouse.imports.WhImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhExportRepository extends JpaRepository<WhExport, Long> {

    @Query(
            value = " select * from tbl_warehouse_export as we " +
                    " where " +
                    " ( " +
                    "   (:#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT}) " +
                    "   or ( " +
                    "        :#{#principal.permission} = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "        and we.warehouse_id in (:#{#principal.warehouseIds}) " +
                    "      ) " +
                    "   or ( " +
                    "        we.create_by = :#{#principal.username} " +
                    "      ) " +
                    " ) " +
                    " and (:#{#dto.keyword} is null or we.code like %:#{#dto.keyword}%) " +
                    " and (:#{#dto.warehouseId} is null or we.warehouse_id = :#{#dto.warehouseId}) " +
                    " and (:#{#dto.productTypeId} is null or we.product_type_id = :#{#dto.productTypeId}) " +
                    " and (:#{#dto.status} is null or we.status = :#{#dto.status?.value}) " +
                    " order by we.create_date desc " +
                    " limit :#{#dto.limit} " +
                    " offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<WhExport> search(WhExportSearchReqDto dto, EbsPrincipal principal);

    @Query(
            value = " select count(1) from tbl_warehouse_export as we " +
                    " where " +
                    " ( " +
                    "   (:#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT}) " +
                    "   or ( " +
                    "        :#{#principal.permission} = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "        and we.warehouse_id in (:#{#principal.warehouseIds}) " +
                    "      ) " +
                    "   or ( " +
                    "        we.create_by = :#{#principal.username} " +
                    "      ) " +
                    " ) " +
                    " and (:#{#dto.keyword} is null or we.code like %:#{#dto.keyword}%) " +
                    " and (:#{#dto.warehouseId} is null or we.warehouse_id = :#{#dto.warehouseId}) " +
                    " and (:#{#dto.productTypeId} is null or we.product_type_id = :#{#dto.productTypeId}) " +
                    " and (:#{#dto.status} is null or we.status = :#{#dto.status?.value}) ",
            nativeQuery = true
    )
    long count(WhExportSearchReqDto dto, EbsPrincipal principal);

    @Query(
            value = " SELECT right(we.code, 5) FROM tbl_warehouse_export AS we " +
                    " ORDER BY right(we.code, 12) DESC " +
                    " LIMIT 1",
            nativeQuery = true
    )
    String getMaxCode();
}
