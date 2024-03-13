package com.example.qlkh.constant.enums.converters;

import com.example.qlkh.constant.enums.WhItemDetailType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class WhItemDetailTypeAttributeConverter implements AttributeConverter<WhItemDetailType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(WhItemDetailType whItemDetailType) {
        if (whItemDetailType == null) {
            return null;
        }
        return whItemDetailType.getValue();
    }

    @Override
    public WhItemDetailType convertToEntityAttribute(Integer integer) {
        return WhItemDetailType.parseByValue(integer);
    }
}
