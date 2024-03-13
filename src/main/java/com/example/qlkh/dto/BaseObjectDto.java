package com.example.qlkh.dto;

import com.example.qlkh.entity.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseObjectDto extends AuditableDto {
    private Long id;

    public BaseObjectDto(BaseObject entity) {
        super(entity);
        if (entity != null) {
            this.id = entity.getId();
        }
    }
}
