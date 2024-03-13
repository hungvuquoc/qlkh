package com.example.qlkh.entity;

import com.example.qlkh.listener.FileDescriptionListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Thông tin file
 */
@Entity
@Table(name = "tbl_file_description")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(FileDescriptionListener.class)
public class FileDescription extends BaseObject {
    @Transient
    private static final long serialVersionUID = 1L;
    @Column(name = "content_type")
    private String contentType;
    @Column(name = "content_size")
    private Long contentSize;
    @Column(name = "name")
    private String name;
    @Column(name = "extension")
    private String extension;
    @Column(name = "file_path")
    private String filePath;
    @Transient
    private boolean isNew; // kiểm tra đây có phải file mới thêm không, khi có lỗi sẽ tìm lại để xóa file

    public FileDescription(Long id) {
        super(id);
    }
}