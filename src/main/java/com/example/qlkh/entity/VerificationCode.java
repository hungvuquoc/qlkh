package com.example.qlkh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

/**
 * Mã xác thực khi tạo tài khoản hoặc đổi mật khẩu
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_verification_codes")
public class VerificationCode extends BaseObject {
    @Transient
    private static final long serialVersionUID = 4572951405687566992L;
    @Column(name = "user_id")
    private Long userId;
    private String username;
    private String email;
    @Column(name = "code_sms")
    private String codeSMS;
    private LocalDateTime expired;
    private Integer type;
}
