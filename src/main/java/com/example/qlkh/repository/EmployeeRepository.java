package com.example.qlkh.repository;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.request.searchs.EmployeeSearchDto;
import com.example.qlkh.dto.request.searchs.SearchDto;
import com.example.qlkh.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(
            value = " with employee_not_select as " +
                    " ( " +
                    "   select distinct e.id from tbl_employee as e " +
                    "   left join tbl_user as user on e.user_id = user.id " +
                    "   left join tbl_user_role as user_rol on user.id = user_rol.user_id " +
                    "   left join tbl_role as rol on user_rol.role_id = rol.id " +
                    "   left join tbl_role_authority as rol_aut on rol.id = rol_aut.role_id " +
                    "   left join tbl_authority as aut on rol_aut.authority_id = aut.id " +
                    "   where rol.name = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT_WITH_PREFIX} " +
                    "   or (" +
                    "       :#{#principal.permission} <> :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT} " +
                    "       and aut.name = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "      ) " +
                    " ) " +
                    " select distinct e.* from tbl_employee as e " +
                    " left join tbl_user as user on e.user_id = user.id " +
                    " left join tbl_user_role as user_rol on user.id = user_rol.user_id " +
                    " left join tbl_role as rol on user_rol.role_id = rol.id " +
                    " left join tbl_role_authority as rol_aut on rol.id = rol_aut.role_id " +
                    " left join tbl_authority as aut on rol_aut.authority_id = aut.id " +
                    " where  " +
                    " ( " +
                    "   :#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT} " +
                    "   or ( " +
                    "        :#{#principal.permission} = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "        and ( " +
                    "               rol.warehouse_id in (:#{#principal.warehouseIds}) " +
                    "               or e.create_by = :#{#principal.username} " +
                    "            ) " +
                    "        and aut.name <> :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "      ) " +
                    " ) " +
                    " and e.id not in (select * from employee_not_select) " +
                    " and (:#{#dto.notAccount} = false or e.user_id is null) " +
                    " order by e.create_date desc " +
                    " limit :#{#dto.getLimit()} " +
                    " offset :#{#dto.getOffset()} ",
            nativeQuery = true
    )
    List<Employee> search(EmployeeSearchDto dto, EbsPrincipal principal);

    @Query(
            value = " with employee_not_select as " +
                    " ( " +
                    "   select distinct e.id from tbl_employee as e " +
                    "   left join tbl_user as user on e.user_id = user.id " +
                    "   left join tbl_user_role as user_rol on user.id = user_rol.user_id " +
                    "   left join tbl_role as rol on user_rol.role_id = rol.id " +
                    "   left join tbl_role_authority as rol_aut on rol.id = rol_aut.role_id " +
                    "   left join tbl_authority as aut on rol_aut.authority_id = aut.id " +
                    "   where rol.name = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT_WITH_PREFIX} " +
                    "   or (" +
                    "       :#{#principal.permission} <> :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT} " +
                    "       and aut.name = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "      ) " +
                    " ) " +
                    " select count(distinct e.id) from tbl_employee as e " +
                    " left join tbl_user as user on e.user_id = user.id " +
                    " left join tbl_user_role as user_rol on user.id = user_rol.user_id " +
                    " left join tbl_role as rol on user_rol.role_id = rol.id " +
                    " left join tbl_role_authority as rol_aut on rol.id = rol_aut.role_id " +
                    " left join tbl_authority as aut on rol_aut.authority_id = aut.id " +
                    " where  " +
                    " ( " +
                    "   :#{#principal.permission} = :#{T(com.example.qlkh.constant.RoleConstant).ROLE_ROOT} " +
                    "   or ( " +
                    "        :#{#principal.permission} = :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "        and ( " +
                    "               rol.warehouse_id in (:#{#principal.warehouseIds}) " +
                    "               or e.create_by = :#{#principal.username} " +
                    "            ) " +
                    "        and aut.name <> :#{T(com.example.qlkh.constant.AuthorityConstant).WH_ROOT} " +
                    "      ) " +
                    " ) " +
                    " and e.id not in (select * from employee_not_select) " +
                    " and (:#{#dto.notAccount} = false or e.user_id is null) ",
            nativeQuery = true
    )
    long count(EmployeeSearchDto dto, EbsPrincipal principal);

    @Query(
            value = " select right(e.code, 5) from tbl_employee as e " +
                    " order by right(e.code, 5) desc " +
                    " limit 1 ",
            nativeQuery = true
    )
    String getMaxCode();

    @Query(
            value = " select if(exists( " +
                    "   select 1 from tbl_employee as e " +
                    "   where e.user_id = :userId" +
                    " ), 'true', 'false') ",
            nativeQuery = true
    )
    boolean userHasUsed(Long userId);

    @Query(
            value = " select if(" +
                    "   exists(select 1 from tbl_warehouse_import as whi where whi.employee_id = :employeeId) " +
                    "   or " +
                    "   exists(select 1 from tbl_warehouse_export as whe where whe.employee_id = :employeeId) " +
                    "   or " +
                    "   exists(select 1 from tbl_warehouse_transfer as wht where wht.employee_id = :employeeId) " +
                    "   or " +
                    "   exists(select 1 from tbl_warehouse_inventory_employee_connect as whiv where whiv.employee_id = :employeeId) " +
                    " , 'true', 'false')",
            nativeQuery = true
    )
    boolean hasUsed(Long employeeId);

    @Query(
            value = " select e.* from tbl_employee as e " +
                    " join tbl_user u on u.id = e.user_id " +
                    " where u.username = :username ",
            nativeQuery = true
    )
    Employee getByUsername(String username);

    @Query(
            value = " select if( " +
                    "   exists(select 1 from tbl_employee as emp where emp.id = :employeeId and emp.user_id is not null ) " +
                    " , 'true', 'false')",
            nativeQuery = true
    )
    boolean hasAccount(Long employeeId);

    @Query(
            value = " select emp.* from tbl_employee as emp " +
                    " where emp.user_id = :userId ",
            nativeQuery = true
    )
    Employee getByUserId(Long userId);

    @Query(
            value = " select emp.code from tbl_employee as emp " +
                    " where emp.id = :employeeId ",
            nativeQuery = true
    )
    String getCodeById(Long employeeId);

    @Query(
            value = " select * from tbl_employee as emp " +
                    " where emp.id in :ids ",
            nativeQuery = true
    )
    List<Employee> getByIds(List<Long> ids);
}
