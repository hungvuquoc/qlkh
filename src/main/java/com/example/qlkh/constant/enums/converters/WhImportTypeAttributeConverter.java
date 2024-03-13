package com.example.qlkh.constant.enums.converters;

import com.example.qlkh.constant.enums.WhImportType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class WhImportTypeAttributeConverter implements AttributeConverter<WhImportType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(WhImportType whImportType) {
        if (whImportType == null) {
            return null;
        }
        return whImportType.getValue();
    }

    @Override
    public WhImportType convertToEntityAttribute(Integer integer) {
        return WhImportType.parseByValue(integer);
    }
}
