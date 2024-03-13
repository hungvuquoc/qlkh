package com.example.qlkh.dto;

import com.example.qlkh.entity.FileDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.ByteArrayOutputStream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileDescriptionDto extends BaseObjectDto {
    private String contentType;
    private Long contentSize;
    private String name;
    private String extension;
    private Integer orderBy;
    @JsonIgnore
    private String filePath;
    @JsonIgnore
    private ByteArrayOutputStream dataByte;

    public FileDescriptionDto(FileDescription entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.name = entity.getName();
            this.contentSize = entity.getContentSize();
            this.contentType = entity.getContentType();
            this.extension = entity.getExtension();
            this.filePath = entity.getFilePath();
        }
    }

    public FileDescriptionDto(FileDescription entity, ByteArrayOutputStream dataByte) {
        this(entity);
        this.dataByte = dataByte;
    }
}