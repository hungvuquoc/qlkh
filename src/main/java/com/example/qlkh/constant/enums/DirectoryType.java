package com.example.qlkh.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * các loại package theo loại file
 * */
@Getter
@AllArgsConstructor
public enum DirectoryType {
    VIDEO("videos"),
    IMAGE("images"),
    DOCUMENT("documents"),
    EXCEL("excels"),
    OTHER("others"),
    ;
    private final String value;

    public static String getByExtension(String extension) {
        if (extension == null)
            return OTHER.value;

        if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png"))
            return IMAGE.value;

        if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx"))
            return EXCEL.value;

        if (extension.equalsIgnoreCase("doc") || extension.equalsIgnoreCase("docx"))
            return DOCUMENT.value;

        if (extension.equalsIgnoreCase("mp4"))
            return VIDEO.value;

        return OTHER.value;
    }
}
