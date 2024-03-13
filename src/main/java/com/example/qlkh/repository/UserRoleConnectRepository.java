package com.example.qlkh.repository;

import com.example.qlkh.entity.UserRoleConnect;
import com.example.qlkh.entity.pk.PkUserRoleConnect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRoleConnectRepository extends JpaRepository<UserRoleConnect, PkUserRoleConnect> {

    @Modifying
    @Query(
            value = " delete use_rol from tbl_user_role as use_rol " +
                    " where use_rol.role_id = :roleId ",
            nativeQuery = true
    )
    void deleteByRoleId(@Param("roleId") Long roleId);

    @Modifying
    @Query(
            value = " delete use_rol from tbl_user_role as use_rol " +
                    " join tbl_role rol on use_rol.role_id = rol.id " +
                    " where use_rol.user_id = :userId and rol.name not in :roleNames ",
            nativeQuery = true
    )
    void deleteByUserId(@Param("userId") Long userId, @Param("roleNames") Collection<String> roleNames);

    @Modifying
    @Query(
            value = " delete use_rol from tbl_user_role as use_rol " +
                    " where use_rol.user_id = :userId ",
            nativeQuery = true
    )
    void deleteByUserId(@Param("userId") Long userId);
}
