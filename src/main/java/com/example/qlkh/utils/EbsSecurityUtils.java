package com.example.qlkh.utils;

import com.example.qlkh.constant.AuthorityConstant;
import com.example.qlkh.constant.RoleConstant;
import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.dto.EbsWarehouse;
import com.example.qlkh.error.status.CommonStatus;
import com.example.qlkh.exception.DataRuntimeException;
import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class EbsSecurityUtils {
    public static final String ROLE_PREFIX = "ROLE_";

    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !Objects.isNull(authentication) && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
    }

    public static Object getPrincipal() {
        if (isAuthenticated()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication.getPrincipal();
        }

        return null;
    }

    public static String getUsername() {
        if (isAuthenticated()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return ((EbsPrincipal) authentication.getPrincipal()).getUsername();
        }

        throw new DataRuntimeException(CommonStatus.FORBIDDEN);
    }

    public static EbsPrincipal getCurrentUser() {
        if (isAuthenticated()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (EbsPrincipal) authentication.getPrincipal();
        }

        throw new DataRuntimeException(CommonStatus.FORBIDDEN);
    }

    private static boolean hasRootRole(Collection<String> roles) {
        return CollectionUtils.isNotEmpty(roles) && roles.contains(ROLE_PREFIX + RoleConstant.ROLE_ROOT);
    }

    private static boolean hasAllWhPermissions(@NonNull Map<Long, EbsWarehouse> warehouseMap, Long warehouseId, String... authorities) {
        EbsWarehouse warehouse = warehouseMap.get(warehouseId);

        return warehouse != null &&
                CollectionUtils.isNotEmpty(warehouse.getAuthorities()) &&
                warehouse.getAuthorities().containsAll(Arrays.asList(authorities));
    }

    private static boolean hasAnyWhPermission(@NonNull Map<Long, EbsWarehouse> warehouseMap, Long warehouseId, String... authorities) {
        EbsWarehouse warehouse = warehouseMap.get(warehouseId);

        return warehouse != null &&
                CollectionUtils.isNotEmpty(warehouse.getAuthorities()) &&
                Arrays.stream(authorities).anyMatch(warehouse.getAuthorities()::contains);
    }

    ///

    public static boolean noneWhPermission(Long warehouseId, @NonNull String... authorities) {
        return !allWhPermission(warehouseId, authorities);
    }

    public static boolean anyWhPermission(Long warehouseId, @NonNull String... authorities) {
        EbsPrincipal principal = getCurrentUser();
        return hasRootRole(principal.getRoles()) ||
                MapUtils.isNotEmpty(principal.getWarehouses()) &&
                        hasAnyWhPermission(principal.getWarehouses(), warehouseId, authorities);
    }

    public static boolean allWhPermission(Long warehouseId, @NonNull String... authorities) {
        EbsPrincipal principal = getCurrentUser();
        return hasRootRole(principal.getRoles()) ||
                MapUtils.isNotEmpty(principal.getWarehouses()) &&
                        hasAllWhPermissions(principal.getWarehouses(), warehouseId, authorities);
    }

    public static boolean hasRootRole() {
        EbsPrincipal principal = getCurrentUser();
        return hasRootRole(principal.getRoles());
    }

    public static boolean hasWhPermission(Long warehouseId, @NonNull String... authorities) {
        EbsPrincipal principal = getCurrentUser();

        return hasRootRole(principal.getRoles()) ||
                MapUtils.isNotEmpty(principal.getWarehouses()) &&
                        (hasAllWhPermissions(principal.getWarehouses(), warehouseId, AuthorityConstant.WH_ROOT) ||
                                hasAnyWhPermission(principal.getWarehouses(), warehouseId, authorities));

    }

    public static boolean hasWhPermission(Long warehouseId) {
        EbsPrincipal principal = getCurrentUser();

        return hasRootRole(principal.getRoles()) ||
                MapUtils.isNotEmpty(principal.getWarehouses()) &&
                        hasAllWhPermissions(principal.getWarehouses(), warehouseId, AuthorityConstant.WH_ROOT);
    }
}
