package com.example.qlkh.dto.response.products;

import com.example.qlkh.dto.BaseObjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUnitRespDto extends BaseObjectDto {
    private Long unitId;
    private String code;
    private String name;
    private Double ratio;
    private boolean isDefault;
    private boolean isUseReport;
    private boolean deleted;

    public String getTag() {
        StringBuilder tag = new StringBuilder();
        tag.append("[").append(this.name).append("]");
        tag.append(" ");
        tag.append("[").append(this.ratio).append("]");

        if (this.isDefault) {
            tag.append(" [Mặc định]");
        }

        if (this.isUseReport) {
            tag.append(" [Báo cáo]");
        }

        return tag.toString();
    }
}
