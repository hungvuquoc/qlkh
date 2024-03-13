package com.example.qlkh.dto.response;

import com.example.qlkh.dto.response.warehouse.WarehouseRespDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRespDto {
    private Long id;
    private String name;
    private String description;
    private WarehouseRespDto warehouse;
    private List<String> authorities;
}
