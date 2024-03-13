package com.example.qlkh.constant.enums.converters;

import com.example.qlkh.constant.enums.WhTransactionStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class WhTransactionStatusAttributeConverter implements AttributeConverter<WhTransactionStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(WhTransactionStatus whTransactionStatus) {
        if (whTransactionStatus == null) {
            return null;
        }
        return whTransactionStatus.getValue();
    }

    @Override
    public WhTransactionStatus convertToEntityAttribute(Integer integer) {
        return WhTransactionStatus.parseByValue(integer);
    }
}
