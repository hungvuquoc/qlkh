package com.example.qlkh.utils;

import java.util.Map;

public class EbsCollectionsUtils {
    public static boolean isNullOrEmpty(Map<? , ?> map) {
        return (map == null || map.isEmpty());
    }

    public static boolean isNotNullOrEmpty(Map<? , ?> map) {
        return !isNullOrEmpty(map);
    }
}
