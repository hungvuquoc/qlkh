package com.example.qlkh.repository;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.dto.response.projection.UserProjectionDto;
import com.example.qlkh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(
            value = " select if( " +
                    "   exists(select 1 from tbl_user as user where user.id <> :userId and user.username = :username) " +
                    " ,'true', 'false' ) ",
            nativeQuery = true
    )
    boolean existsByIdAndUsername(@Param("userId") Long userId, @Param("username") String username);

    @Query(
            value = " select if( " +
                    "   exists(select 1 from tbl_user as user where user.id <> :userId and user.email = :email) " +
                    " , 'true', 'false') ",
            nativeQuery = true
    )
    boolean existsByIdAndEmail(@Param("userId") Long userId, @Param("email") String email);

    @Modifying
    @Query("update User u SET u.failedAttempt = ?1 WHERE u.username = ?2")
    void updateFailedAttempts(byte failAttempts, String username);

    @Modifying
    @Query("update User u SET u.lockTime = ?1 WHERE u.username = ?2")
    void updateLockedTime(LocalDateTime time, String username);

    @Query(
            value = " with user_not_select as " +
                    " ( " +
                    "   select distinct user.id from tbl_user as user " +
                    "   left join tbl_user_role as user_rol on user.id = user_rol.user_id " +
                    "   left join tbl_role as rol on user_rol.role_id = rol.id " +
                    "   left join tbl_role_authority as rol_aut on rol.id = rol_aut.role_id " +
                    "   left join tbl_authority as aut on rol_aut.authority_id = aut.id " +
                    "   where rol.name = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT_WITH_PREFIX} " +
                    "   or ( " +
                    "       :#{#principal.permission} <> :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT} " +
                    "       and aut.name = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "      ) " +
                    " ) " +
                    " select " +
                    " user.id, " +
                    " user.username, " +
                    " user.email, " +
                    " user.active, " +
                    " emp.code as employeeCode, " +
                    " emp.name as employeeName " +
                    " from tbl_user as user " +
                    " join tbl_employee as emp on user.id = emp.user_id " +
                    " left join tbl_user_role user_rol on user.id = user_rol.user_id " +
                    " left join tbl_role rol on user_rol.role_id = rol.id " +
                    " where " +
                    " ( " +
                    "   (:#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT}) " +
                    "   or ( " +
                    "        :#{#principal.permission} = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "        and ( " +
                    "               rol.warehouse_id in (:#{#principal.warehouseIds}) " +
                    "               or user.create_by = :#{#principal.username} " +
                    "            ) " +
                    "      ) " +
                    " ) " +
                    " and user.id not in (select * from user_not_select) " +
                    " and ( " +
                    "   :#{#dto.keyword} is null" +
                    "   or user.username like %:#{#dto.keyword}% " +
                    "   or user.email like %:#{#dto.keyword}% " +
                    " ) " +
                    " group by user.id, user.username, user.email, user.active, emp.code, emp.name " +
                    " order by user.id desc " +
                    " limit :#{#dto.limit} " +
                    " offset :#{#dto.offset} ",
            nativeQuery = true
    )
    List<UserProjectionDto> search(SearchDto dto, EbsPrincipal principal);

    @Query(
            value = " with user_not_select as " +
                    " ( " +
                    "   select distinct user.id from tbl_user as user " +
                    "   left join tbl_user_role as user_rol on user.id = user_rol.user_id " +
                    "   left join tbl_role as rol on user_rol.role_id = rol.id " +
                    "   left join tbl_role_authority as rol_aut on rol.id = rol_aut.role_id " +
                    "   left join tbl_authority as aut on rol_aut.authority_id = aut.id " +
                    "   where rol.name = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT_WITH_PREFIX} " +
                    "   or ( " +
                    "       :#{#principal.permission} <> :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT} " +
                    "       and aut.name = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "      ) " +
                    " ) " +
                    " select count(distinct user.id) from tbl_user as user " +
                    " left join tbl_user_role user_rol on user.id = user_rol.user_id " +
                    " left join tbl_role rol on user_rol.role_id = rol.id " +
                    " where " +
                    " ( " +
                    "   (:#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT}) " +
                    "   or ( " +
                    "        :#{#principal.permission} = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "        and ( " +
                    "               rol.warehouse_id in (:#{#principal.warehouseIds}) " +
                    "               or user.create_by = :#{#principal.username} " +
                    "            ) " +
                    "      ) " +
                    " ) " +
                    " and user.id not in (select * from user_not_select) " +
                    " and ( " +
                    "   :#{#dto.keyword} is null" +
                    "   or user.username like %:#{#dto.keyword}% " +
                    "   or user.email like %:#{#dto.keyword}% " +
                    " ) ",
            nativeQuery = true
    )
    long count(SearchDto dto, EbsPrincipal principal);
}
