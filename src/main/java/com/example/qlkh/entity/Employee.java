package com.example.qlkh.entity;

import com.example.qlkh.constant.enums.GenderType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tbl_employee")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseObject {
    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String code;
    private String name;
    private LocalDate birthday;
    private GenderType gender;
    private String phone;
    private boolean deleted;
}
