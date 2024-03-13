package com.example.qlkh.repository.warehouse.transfers;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.request.searchs.WhTransferSearchReqDto;
import com.example.qlkh.entity.warehouse.transfers.WhTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhTransferRepository extends JpaRepository<WhTransfer, Long> {

    @Query(
            value = " select * from tbl_warehouse_transfer as tra " +
                    " where " +
                    " ( " +
                    "   (:#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT}) " +
                    "   or ( " +
                    "        :#{#principal.permission} = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "        and ( " +
                    "               tra.wh_export_id in (:#{#principal.warehouseIds}) " +
                    "               or tra.wh_import_id in (:#{#principal.warehouseIds}) " +
                    "            ) " +
                    "      ) " +
                    "   or (tra.wh_export_id in (:#{#principal.warehouseIds}) and tra.create_by = :#{#principal.username} ) " +
                    "   or (tra.wh_export_id <> tra.wh_import_id and tra.wh_import_id in (:#{#principal.warehouseIds})) " +
                    " ) " +
                    " and (:#{#dto.keyword} is null or tra.code like %:#{#dto.keyword}%) " +
                    " and (:#{#dto.warehouseImportId} is null or tra.wh_import_id = :#{#dto.warehouseImportId}) " +
                    " and (:#{#dto.warehouseExportId} is null or tra.wh_export_id = :#{#dto.warehouseExportId}) " +
                    " and (:#{#dto.productTypeId} is null or tra.product_type_id = :#{#dto.productTypeId}) " +
                    " and (:#{#dto.status} is null or tra.status = :#{#dto.status?.value}) " +
                    " order by tra.create_date desc " +
                    " limit :#{#dto.limit} " +
                    " offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<WhTransfer> search(WhTransferSearchReqDto dto, EbsPrincipal principal);

    @Query(
            value = " select count(1) from tbl_warehouse_transfer as tra " +
                    " where " +
                    " ( " +
                    "   (:#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT}) " +
                    "   or ( " +
                    "        :#{#principal.permission} = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "        and ( " +
                    "               tra.wh_export_id in (:#{#principal.warehouseIds}) " +
                    "               or tra.wh_import_id in (:#{#principal.warehouseIds}) " +
                    "            ) " +
                    "      ) " +
                    "   or (tra.wh_export_id in (:#{#principal.warehouseIds}) and tra.create_by = :#{#principal.username} ) " +
                    "   or (tra.wh_export_id <> tra.wh_import_id and tra.wh_import_id in (:#{#principal.warehouseIds})) " +
                    " ) " +
                    " and (:#{#dto.keyword} is null or tra.code like %:#{#dto.keyword}%) " +
                    " and (:#{#dto.warehouseImportId} is null or tra.wh_import_id = :#{#dto.warehouseImportId}) " +
                    " and (:#{#dto.warehouseExportId} is null or tra.wh_export_id = :#{#dto.warehouseExportId}) " +
                    " and (:#{#dto.productTypeId} is null or tra.product_type_id = :#{#dto.productTypeId}) " +
                    " and (:#{#dto.status} is null or tra.status = :#{#dto.status?.value}) ",
            nativeQuery = true
    )
    long count(WhTransferSearchReqDto dto, EbsPrincipal principal);

    @Query(
            value = " SELECT right(wt.code, 5) FROM tbl_warehouse_transfer AS wt " +
                    " ORDER BY right(wt.code, 12) DESC " +
                    " LIMIT 1",
            nativeQuery = true
    )
    String getMaxCode();
}
