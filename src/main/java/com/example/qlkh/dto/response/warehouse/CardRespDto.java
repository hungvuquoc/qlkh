package com.example.qlkh.dto.response.warehouse;

import com.example.qlkh.constant.DateConstants;
import com.example.qlkh.utils.serializers.EbsDoubleSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardRespDto {
    @JsonFormat(pattern = DateConstants.US_NORMAL_DATE)
    private LocalDateTime createDate;
    private String inCode;
    private String outCode;
    private String note;
    @JsonFormat(pattern = DateConstants.US_NORMAL_DATE)
    private LocalDateTime inputDate;
    @JsonSerialize(using = EbsDoubleSerializer.class)
    private Double inQuantity;
    @JsonSerialize(using = EbsDoubleSerializer.class)
    private Double outQuantity;
    @JsonSerialize(using = EbsDoubleSerializer.class)
    private Double stockQuantity;
}
