package com.example.qlkh.entity;

import com.example.qlkh.utils.EbsFileUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Các file cũ cần xóa
 * Lý do cần bảng này: khi xóa nếu không may có lỗi thì tập tin vẫn còn
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_file_old")
@AllArgsConstructor
@NoArgsConstructor
public class FileCache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private String path;

    @PreRemove
    public void preRemove() {
        EbsFileUtils.delete(path);
    }
}
