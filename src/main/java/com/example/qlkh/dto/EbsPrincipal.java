package com.example.qlkh.dto;

import com.example.qlkh.constant.AuthorityConstant;
import com.example.qlkh.constant.RoleConstant;
import com.example.qlkh.utils.EbsSecurityUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EbsPrincipal implements Serializable {
    private String username;
    private String email;
    private Set<String> roles;
    private Set<String> authorities;
    private Map<Long, EbsWarehouse> warehouses;

    @JsonIgnore
    public String getPermission() {
        if (roles != null && roles.contains(EbsSecurityUtils.ROLE_PREFIX + RoleConstant.ROLE_ROOT)) {
            return RoleConstant.ROLE_ROOT;
        }

        if (authorities != null && authorities.contains(AuthorityConstant.WH_ROOT)) {
            return AuthorityConstant.WH_ROOT;
        }

        return null;
    }

    @JsonIgnore
    public Long[] getWarehouseIds() {
        if (MapUtils.isEmpty(warehouses)) {
            return null;
        }
        Set<Long> warehouseIds = new HashSet<>();

        for (Long key : warehouses.keySet()) {
//            EbsWarehouse warehouse = warehouses.get(key);
//            if (CollectionUtils.isNotEmpty(warehouse.getAuthorities()) &&
//                    warehouse.getAuthorities().contains(AuthorityConstant.WH_ROOT)) {
                warehouseIds.add(key);
//            }
        }

        if (CollectionUtils.isNotEmpty(warehouseIds)) {
            return warehouseIds.toArray(new Long[0]);
        }

        return null;
    }
}
