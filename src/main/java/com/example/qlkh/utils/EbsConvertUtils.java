package com.example.qlkh.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class EbsConvertUtils {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final JavaTimeModule timeModule = new JavaTimeModule();

    static {
        mapper.registerModule(timeModule);
    }

    public static <T> T deserializeJson(String content, Class<T> responseType) {
        try {
            return mapper.readValue(content, responseType);
        } catch (Exception e) {
            log.error(" EbsConvertUtils method deserializeJson, ERROR: {}", e.getMessage(), e);
            return null;
        }
    }

    public static <T> T deserializeJson(String content, TypeReference<T> valueTypeRe) {
        try {
            return mapper.readValue(content, valueTypeRe);
        } catch (Exception e) {
            log.error(" EbsConvertUtils method deserializeJson, ERROR: {}", e.getMessage(), e);
            return null;
        }
    }

    public static String toString(Object obj) {
        try {
            if (org.springframework.util.ObjectUtils.isEmpty(obj)) {
                return null;
            }
            return mapper.writeValueAsString(obj).replace("\n", "");
        } catch (JsonProcessingException e) {
            log.error("EbsConvertUtils method toString, ERROR: {}", e.getMessage());
            return "{}";
        }
    }

    public static Map<String, Object> toMap(Object obj) {
        try {
            if (org.springframework.util.ObjectUtils.isEmpty(obj)) {
                return null;
            }
            return mapper.convertValue(obj, new TypeReference<Map<String, Object>>(){});
        } catch (IllegalArgumentException e) {
            log.error("EbsConvertUtils method toMap, ERROR: {}", e.getMessage());
            return null;
        }
    }
}
