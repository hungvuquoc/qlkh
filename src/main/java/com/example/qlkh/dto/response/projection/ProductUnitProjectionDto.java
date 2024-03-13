package com.example.qlkh.dto.response.projection;

public interface ProductUnitProjectionDto {
    Long getId();
    Long getUnitId();
    String getUnitCode();
    String getUnitName();
    Double getRatio();
    boolean getDefault();
    boolean getUseReport();
    boolean getDeleted();

    default String getTag() {
        StringBuilder tag = new StringBuilder();
        tag.append("[").append(this.getUnitName()).append("]");
        tag.append(" ");
        tag.append("[").append(this.getRatio()).append("]");

        if (this.getDefault()) {
            tag.append(" [Mặc định]");
        }

        if (this.getUseReport()) {
            tag.append(" [Báo cáo]");
        }

        return tag.toString();
    }
}
