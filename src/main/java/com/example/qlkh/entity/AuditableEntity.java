package com.example.qlkh.entity;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.listener.AuditListenerEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({AuditListenerEntity.class})
public class AuditableEntity implements Serializable {
    @Transient
    public static final long serialVersionUID = 4559994432567537044L;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.US_NORMAL_DATE_TIME)
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
    @Column(name = "create_by", nullable = false, length = 100)
    private String createBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.US_NORMAL_DATE_TIME)
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    @Column(name = "modify_date")
    private LocalDateTime modifyDate;
    @Column(name = "modify_by", length = 100)
    private String modifyBy;

    public AuditableEntity(AuditableEntity entity) {
        if (entity != null){
            this.createDate = entity.getCreateDate();
            this.createBy = entity.getCreateBy();
            this.modifyDate = entity.getModifyDate();
            this.modifyBy = entity.getModifyBy();
        }
    }
}
