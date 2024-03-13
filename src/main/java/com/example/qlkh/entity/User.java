package com.example.qlkh.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Tài khoản đăng nhập
 */
@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseObject implements UserDetails {
    @Transient
    private static final long serialVersionUID = 4572941405687566992L;

    @Column(length = 100, nullable = false, unique = true)
    private String username;

    @Column(length = 150, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserRoleConnect> roles;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserAuthorityConnect> authorities;

    @Transient
    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    @Transient
    private boolean credentialsNonExpired = true;

    @Column(nullable = false)
    private boolean active = true;

    @Column(name = "need_fix_pass", columnDefinition = "bit default false")
    private Boolean needFixPass = false;

    @Column(name = "lock_time")
    private LocalDateTime lockTime;

    @Column(name = "failed_attempt")
    private byte failedAttempt = 0;

    public List<UserAuthorityConnect> getAuthorityEntities() {
        return this.authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> allAuthorities = new HashSet<>();
        if (CollectionUtils.isNotEmpty(this.authorities)) {
            for (UserAuthorityConnect authorityConnect : this.authorities) {
                allAuthorities.add(authorityConnect.getAuthority());
            }
        }

        if (CollectionUtils.isEmpty(this.roles)) {
            return allAuthorities;
        }

        for (UserRoleConnect roleConnect : this.roles) {
            List<RoleAuthorityConnect> authorityConnects = roleConnect.getRole().getAuthorities();
            if (CollectionUtils.isEmpty(authorityConnects)) {
                continue;
            }
            for (RoleAuthorityConnect authorityConnect : authorityConnects) {
                allAuthorities.add(authorityConnect.getAuthority());
            }
        }
 
        return allAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
