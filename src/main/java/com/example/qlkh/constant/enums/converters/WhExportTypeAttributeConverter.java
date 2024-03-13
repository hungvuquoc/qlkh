package com.example.qlkh.constant.enums.converters;

import com.example.qlkh.constant.enums.WhExportType;
import com.example.qlkh.constant.enums.WhImportType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class WhExportTypeAttributeConverter implements AttributeConverter<WhExportType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(WhExportType whImportType) {
        if (whImportType == null) {
            return null;
        }
        return whImportType.getValue();
    }

    @Override
    public WhExportType convertToEntityAttribute(Integer integer) {
        return WhExportType.parseByValue(integer);
    }
}
