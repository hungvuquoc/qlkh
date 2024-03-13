package com.example.qlkh.entity;

import com.example.qlkh.entity.pk.PkRoleAuthorityConnect;
import com.example.qlkh.entity.pk.PkUserRoleConnect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "tbl_user_role")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PkUserRoleConnect.class)
public class UserRoleConnect {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
}
