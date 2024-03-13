package com.example.qlkh.listener;

import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.entity.AuditableEntity;
import com.example.qlkh.utils.EbsSecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.PostRemove;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
public class AuditListenerEntity {
    @PostRemove
    public void remove(AuditableEntity auditableEntity) {
        LocalDateTime now = LocalDateTime.now();
        auditableEntity.setModifyDate(now);
        Object principal = EbsSecurityUtils.getPrincipal();
        if (!Objects.isNull(principal)) {
            if (principal instanceof UserDetails) {
                EbsPrincipal user = (EbsPrincipal) principal;
                log.info("After Remove " + auditableEntity + " by" + user.getUsername());
            } else {
                log.info("After Remove " + auditableEntity + " by" + principal);
            }
        }
    }

    @PrePersist
    public void create(AuditableEntity auditableEntity) {
        LocalDateTime now = LocalDateTime.now();
        auditableEntity.setCreateDate(now);
//        auditableEntity.setModifyDate(now);
        Object principal = EbsSecurityUtils.getPrincipal();
        if (!Objects.isNull(principal)) {
            if (principal instanceof EbsPrincipal) {
                EbsPrincipal user = (EbsPrincipal) principal;
                auditableEntity.setCreateBy(user.getUsername());
//                auditableEntity.setModifyBy(user.getUsername());
            } else {
                auditableEntity.setCreateBy(principal.toString());
//                auditableEntity.setModifyBy(principal.toString());
            }
        } else {
            auditableEntity.setCreateBy("admin");
        }
    }

    @PreUpdate
    public void update(AuditableEntity auditableEntity) {
        LocalDateTime now = LocalDateTime.now();
        auditableEntity.setModifyDate(now);
        Object principal = EbsSecurityUtils.getPrincipal();
        if (!Objects.isNull(principal)) {
            if (principal instanceof EbsPrincipal) {
                EbsPrincipal user = (EbsPrincipal) principal;
                auditableEntity.setModifyBy(user.getUsername());
            } else {
                auditableEntity.setModifyBy(principal.toString());
            }
        }
    }
}
