package com.example.qlkh.repository;

import com.example.qlkh.entity.UserAuthorityConnect;
import com.example.qlkh.entity.pk.PkUserAuthorityConnect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserAuthorityConnectRepository extends JpaRepository<UserAuthorityConnect, PkUserAuthorityConnect> {
    @Modifying
    @Query(
            value = " delete use_aut from tbl_user_authority as use_aut " +
                    " join tbl_authority aut on use_aut.authority_id = aut.id " +
                    " where use_aut.user_id = :userId and aut.name not in :authorityNames ",
            nativeQuery = true
    )
    void deleteByUserId(@Param("userId") Long userId, @Param("authorityNames") Collection<String> authorityNames);

    @Modifying
    @Query(
            value = " delete use_aut from tbl_user_authority as use_aut " +
                    " where use_aut.user_id = :userId ",
            nativeQuery = true
    )
    void deleteByUserId(@Param("userId") Long userId);
}
