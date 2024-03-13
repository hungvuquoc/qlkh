package com.example.qlkh.entity.warehouse.transfers;

import com.example.qlkh.constant.enums.WhItemDetailType;
import com.example.qlkh.constant.enums.converters.WhItemDetailTypeAttributeConverter;
import com.example.qlkh.entity.warehouse.WarehouseFloor;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tbl_warehouse_transfer_item_detail")
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)
public class WhTransferItemDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private WhTransferItem parent;
    @ManyToOne
    @JoinColumn(name = "floor_id", referencedColumnName = "id")
    private WarehouseFloor floor;
    @Column(name = "floor_name")
    private String floorName;
    @Column(name = "quantity")
    private Double quantity;
    @Column(name = "input_date")
    private LocalDateTime inputDate;
    @Column(name = "type")
    @Convert(converter = WhItemDetailTypeAttributeConverter.class)
    private WhItemDetailType type;
}
