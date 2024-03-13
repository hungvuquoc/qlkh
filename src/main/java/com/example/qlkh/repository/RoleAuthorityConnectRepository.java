package com.example.qlkh.repository;

import com.example.qlkh.entity.RoleAuthorityConnect;
import com.example.qlkh.entity.pk.PkRoleAuthorityConnect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleAuthorityConnectRepository extends JpaRepository<RoleAuthorityConnect, PkRoleAuthorityConnect> {
    @Modifying
    @Query(
            value = " delete rol_aut from tbl_role_authority as rol_aut " +
                    " join tbl_authority aut on aut.id = rol_aut.authority_id " +
                    " where rol_aut.role_id = :roleId and aut.name not in :authorityNames ",
            nativeQuery = true
    )
    void deleteByRoleId(Long roleId, List<String> authorityNames);

    @Modifying
    @Query(
            value = " delete rol_aut from tbl_role_authority as rol_aut " +
                    " where rol_aut.role_id = :roleId ",
            nativeQuery = true
    )
    void deleteByRoleId(Long roleId);
}
