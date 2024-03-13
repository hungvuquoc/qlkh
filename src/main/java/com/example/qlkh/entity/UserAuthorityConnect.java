package com.example.qlkh.entity;

import com.example.qlkh.entity.pk.PkRoleAuthorityConnect;
import com.example.qlkh.entity.pk.PkUserAuthorityConnect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "tbl_user_authority")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PkUserAuthorityConnect.class)
public class UserAuthorityConnect {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "authority_id", referencedColumnName = "id")
    private Authority authority;
}
