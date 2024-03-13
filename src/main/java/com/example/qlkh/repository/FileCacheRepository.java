package com.example.qlkh.repository;

import com.example.qlkh.entity.FileCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface FileCacheRepository extends JpaRepository<FileCache, Long> {
    void deleteByCreatedAtBefore(@Param("thresholdTime") LocalDateTime thresholdTime);
}
