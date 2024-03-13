package com.example.qlkh.repository.warehouse.imports;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.request.searchs.WhImportSearchReqDto;
import com.example.qlkh.entity.warehouse.imports.WhImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhImportRepository extends JpaRepository<WhImport, Long> {

    @Query(
            value = " select * from tbl_warehouse_import as wi " +
                    " where" +
                    " ( " +
                    "   (:#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT}) " +
                    "   or ( " +
                    "        :#{#principal.permission} = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "        and wi.warehouse_id in (:#{#principal.warehouseIds}) " +
                    "      ) " +
                    "   or ( " +
                    "        wi.create_by = :#{#principal.username} " +
                    "      ) " +
                    " ) " +
                    " and (:#{#dto.keyword} is null or :#{#dto.keyword} = '' or wi.code like %:#{#dto.keyword}% ) " +
                    " and (:#{#dto.warehouseId} is null or wi.warehouse_id = :#{#dto.warehouseId}) " +
                    " and (:#{#dto.supplierId} is null or wi.supplier_id = :#{#dto.supplierId}) " +
                    " and (:#{#dto.productTypeId} is null or wi.product_type_id = :#{#dto.productTypeId}) " +
                    " and (:#{#dto.status} is null or wi.status = :#{#dto.status?.value}) " +
                    " order by wi.create_date desc " +
                    " limit :#{#dto.limit} " +
                    " offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<WhImport> search(WhImportSearchReqDto dto, EbsPrincipal principal);

    @Query(
            value = " select count(1) from tbl_warehouse_import as wi " +
                    " where " +
                    " ( " +
                    "   (:#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT}) " +
                    "   or ( " +
                    "        :#{#principal.permission} = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "        and wi.warehouse_id in (:#{#principal.warehouseIds}) " +
                    "      ) " +
                    "   or ( " +
                    "        wi.create_by = :#{#principal.username} " +
                    "      ) " +
                    " ) " +
                    " and (:#{#dto.keyword} is null or :#{#dto.keyword} = '' or wi.code like %:#{#dto.keyword}% ) " +
                    " and (:#{#dto.warehouseId} is null or wi.warehouse_id = :#{#dto.warehouseId}) " +
                    " and (:#{#dto.supplierId} is null or wi.supplier_id = :#{#dto.supplierId}) " +
                    " and (:#{#dto.productTypeId} is null or wi.product_type_id = :#{#dto.productTypeId}) " +
                    " and (:#{#dto.status} is null or wi.status = :#{#dto.status?.value}) ",
            nativeQuery = true
    )
    long count(WhImportSearchReqDto dto, EbsPrincipal principal);

    @Query(
            value = " SELECT right(wi.code, 5) FROM tbl_warehouse_import AS wi " +
                    " ORDER BY right(wi.code, 12) DESC " +
                    " LIMIT 1",
            nativeQuery = true
    )
    String getMaxCode();
}
