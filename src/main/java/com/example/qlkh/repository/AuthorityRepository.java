package com.example.qlkh.repository;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query(
            value = " select aut.* from tbl_authority as aut " +
                    " where aut.name in :names ",
            nativeQuery = true
    )
    List<Authority> getByNames(Collection<String> names);

    @Query(
            value = " select if( " +
                    "   exists(select 1 from tbl_authority as aut where aut.name in :names) " +
                    " , 'true', 'false') ",
            nativeQuery = true
    )
    boolean existsByNames(List<String> names);

    @Query(
            value = " select aut.* from tbl_authority as aut " +
                    " where " +
                    " ( " +
                    "   (:#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT}) " +
                    "   or ( " +
                    "        :#{#principal.permission} = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "        and aut.name <> :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "      ) " +
                    " ) " +
                    " and (:#{#dto.keyword} is null or aut.name like %:#{#dto.keyword}% ) " +
                    " limit :#{#dto.limit} " +
                    " offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<Authority> search(SearchDto dto, EbsPrincipal principal);

    @Query(
            value = " select count(1) from tbl_authority as aut " +
                    " where " +
                    " ( " +
                    "   (:#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT}) " +
                    "   or ( " +
                    "        :#{#principal.permission} = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "        and aut.name <> :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "      ) " +
                    " ) " +
                    " and (:#{#dto.keyword} is null or aut.name like %:#{#dto.keyword}% ) ",
            nativeQuery = true
    )
    long count(SearchDto dto, EbsPrincipal principal);
}
