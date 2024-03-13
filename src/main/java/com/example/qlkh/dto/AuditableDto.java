package com.example.qlkh.dto;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.entity.AuditableEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AuditableDto implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.US_NORMAL_DATE_TIME)
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    protected LocalDateTime createDate;

    protected String createBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.US_NORMAL_DATE_TIME)
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    protected LocalDateTime modifyDate;

    protected String modifyBy;

    public AuditableDto(AuditableEntity entity) {
        if (entity != null) {
            this.createDate = entity.getCreateDate();
            this.createBy = entity.getCreateBy();
            this.modifyDate = entity.getModifyDate();
            this.modifyBy = entity.getModifyBy();
        }
    }
}
