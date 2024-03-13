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
public class ProductDetailRespDto extends BaseObjectDto {
    private Double price;
    private String quality;
    private String size;

    public String getTag() {
        StringBuilder tag = new StringBuilder();
        if (this.size == null) {
            tag.append("[N/A]");
        } else {
            tag.append("[").append(this.size).append("]");
        }
        tag.append(" ");
        if (this.quality == null) {
            tag.append("[N/A]");
        } else {
            tag.append("[").append(this.quality).append("]");
        }
        tag.append(" ");
        if (this.price == null) {
            tag.append("[N/A]");
        } else {
            tag.append("[").append(this.price).append("]");
        }

        return tag.toString();
    }
}
