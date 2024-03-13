package com.example.qlkh.repository.warehouse.transfers;

import com.example.qlkh.entity.warehouse.transfers.WhTransferItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface WhTransferItemRepository extends JpaRepository<WhTransferItem, Long> {
    @Modifying
    void deleteByParentId(Long parentId);
}
