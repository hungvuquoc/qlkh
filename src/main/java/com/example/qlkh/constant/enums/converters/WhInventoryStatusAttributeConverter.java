package com.example.qlkh.constant.enums.converters;

import com.example.qlkh.constant.enums.WhInventoryStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class WhInventoryStatusAttributeConverter implements AttributeConverter<WhInventoryStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(WhInventoryStatus whInventoryStatus) {
        if (whInventoryStatus == null) {
            return null;
        }
        return whInventoryStatus.getValue();
    }

    @Override
    public WhInventoryStatus convertToEntityAttribute(Integer integer) {
        return WhInventoryStatus.parseByValue(integer);
    }
}
