package com.example.qlkh.repository;

import com.example.qlkh.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Query(
            value = " select org.* from tbl_organization as org " +
                    " limit 1 ",
            nativeQuery = true
    )
    Organization getInfo();
}
