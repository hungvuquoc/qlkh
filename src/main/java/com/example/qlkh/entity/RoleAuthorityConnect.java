package com.example.qlkh.entity;

import com.example.qlkh.entity.pk.PkRoleAuthorityConnect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tbl_role_authority")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PkRoleAuthorityConnect.class)
public class RoleAuthorityConnect implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
    @Id
    @ManyToOne
    @JoinColumn(name = "authority_id", referencedColumnName = "id")
    private Authority authority;
}
