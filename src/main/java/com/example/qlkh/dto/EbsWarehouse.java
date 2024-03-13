package com.example.qlkh.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EbsWarehouse implements Serializable {
    private Long id;
    private String code;
    private String name;
    private Set<String> authorities;
}
