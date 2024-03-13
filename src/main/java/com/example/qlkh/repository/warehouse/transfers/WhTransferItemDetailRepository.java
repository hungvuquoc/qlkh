package com.example.qlkh.repository.warehouse.transfers;

import com.example.qlkh.entity.warehouse.transfers.WhTransferItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WhTransferItemDetailRepository extends JpaRepository<WhTransferItemDetail, Long> {
    @Modifying
    @Query(
            value = " delete tra_ite_det from tbl_warehouse_transfer_item_detail as tra_ite_det " +
                    " join tbl_warehouse_transfer_item as tra_ite on tra_ite.id = tra_ite_det.parent_id " +
                    " where tra_ite.parent_id = :id ",
            nativeQuery = true
    )
    void deleteByWhTransferId(Long id);
}
