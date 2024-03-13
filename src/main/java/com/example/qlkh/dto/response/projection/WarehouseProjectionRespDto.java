package com.example.qlkh.dto.response.projection;

import com.example.qlkh.dto.response.warehouse.WarehouseAreaRespDto;
import com.example.qlkh.utils.EbsConvertUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.util.StringUtils;

import java.util.List;

public interface WarehouseProjectionRespDto {
    Long getId();
    String getCode();
    String getName();
    String getAddress();
    Integer getRowNumber();
    Integer getColumnNumber();
    Boolean getDeleted();
    @JsonIgnore

    String getJsonAreas();

    default List<WarehouseAreaRespDto> getAreas() {
        if (StringUtils.isEmpty(this.getJsonAreas())) {
            return null;
        }
        return EbsConvertUtils.deserializeJson(this.getJsonAreas(), new TypeReference<List<WarehouseAreaRespDto>>() {});
    }
}
