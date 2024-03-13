package com.example.qlkh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tbl_organization")
@AllArgsConstructor
@NoArgsConstructor
public class Organization implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    private String code;
    private String name;
    @Column(name = "enterprise_code")
    private String enterpriseCode;
    @Column(name = "manager_name")
    private String managerName;
    private String address;
    private String phone;
    private String email;
    private String description;
}
