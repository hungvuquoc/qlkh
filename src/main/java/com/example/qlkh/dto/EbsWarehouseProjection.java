package com.example.qlkh.dto;

import com.example.qlkh.utils.EbsConvertUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Set;

public interface EbsWarehouseProjection {
    Long getId();

    String getCode();

    String getName();

    String getJsonAuthorities();

    default Set<String> getAuthorities() {
        if (StringUtils.isEmpty(this.getJsonAuthorities())) {
            return Collections.emptySet();
        }

        return EbsConvertUtils.deserializeJson(this.getJsonAuthorities(), new TypeReference<Set<String>>() {
        });
    }
}
