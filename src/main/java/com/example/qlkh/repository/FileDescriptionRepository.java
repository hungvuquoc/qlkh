package com.example.qlkh.repository;

import com.example.qlkh.entity.FileDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDescriptionRepository extends JpaRepository<FileDescription, Long> {
}
