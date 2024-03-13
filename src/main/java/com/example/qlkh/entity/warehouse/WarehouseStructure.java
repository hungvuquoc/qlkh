package com.example.qlkh.entity.warehouse;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.utils.EbsSecurityUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_warehouse_structure")
public class WarehouseStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.US_NORMAL_DATE_TIME)
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
    @Column(name = "create_by", nullable = false, length = 100)
    private String createBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;
    @Column(name = "row_num")
    private Integer rowNumber;
    @Column(name = "column_num")
    private Integer columnNumber;

    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
        String username = EbsSecurityUtils.getUsername();
        if (StringUtils.isEmpty(username)) {
            createBy = "root";
        } else {
            createBy = username;
        }
    }
}
