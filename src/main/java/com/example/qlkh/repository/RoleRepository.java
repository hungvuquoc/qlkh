package com.example.qlkh.repository;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(
            value = " select if( " +
                    "   exists(select 1 from tbl_role as rol where (:id is null or rol.id <> :id) and rol.name = :name ) " +
                    " , 'true', 'false') ",
            nativeQuery = true
    )
    boolean hasName(@Param("id") Long id, @Param("name") String name);

    @Query(
            value = " select distinct rol.* from tbl_role as rol " +
                    " left join tbl_warehouse as wh on wh.id = rol.warehouse_id " +
                    " left join tbl_role_authority as rol_aut on rol.id = rol_aut.role_id " +
                    " left join tbl_authority as aut on rol_aut.authority_id = aut.id " +
                    " where rol.name <> :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT_WITH_PREFIX} " +
                    " and ( " +
                    "       (:#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT}) " +
                    "       or ( " +
                    "            :#{#principal.permission} = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "            and rol.warehouse_id in (:#{#principal.warehouseIds})" +
                    "            and aut.name <> :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "          ) " +
                    " ) " +
                    " and (" +
                    "       :#{#dto.keyword} is null " +
                    "       or rol.name like %:#{#dto.keyword}% " +
                    "       or wh.name like %:#{#dto.keyword}% " +
                    " ) " +
                    " order by rol.create_date desc " +
                    " limit :#{#dto.limit} " +
                    " offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<Role> search(SearchDto dto, EbsPrincipal principal);

    @Query(
            value = " select count(distinct rol.id) from tbl_role as rol " +
                    " left join tbl_warehouse as wh on wh.id = rol.warehouse_id " +
                    " left join tbl_role_authority as rol_aut on rol.id = rol_aut.role_id " +
                    " left join tbl_authority as aut on rol_aut.authority_id = aut.id" +
                    " where rol.name <> :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT_WITH_PREFIX} " +
                    " and ( " +
                    "       (:#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT}) " +
                    "       or ( " +
                    "            :#{#principal.permission} = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "            and rol.warehouse_id in (:#{#principal.warehouseIds})" +
                    "            and aut.name <> :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "          ) " +
                    " ) " +
                    " and (" +
                    "       :#{#dto.keyword} is null " +
                    "       or rol.name like %:#{#dto.keyword}% " +
                    "       or wh.name like %:#{#dto.keyword}% " +
                    " ) ",
            nativeQuery = true
    )
    long count(SearchDto dto, EbsPrincipal principal);

    @Query(
            value = " select distinct rol.* from tbl_role as rol " +
                    " where rol.name in :names",
            nativeQuery = true
    )
    Set<Role> getByNames(Collection<String> names);
}
