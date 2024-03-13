package com.example.qlkh.constant;

/**
 * Các hằng số
 * */
public class Variables {
    /*REGEX*/
    public static final String REGEX_EMAIL = "^[a-zA-Z]+[a-zA-Z0-9]*(\\.[a-zA-Z0-9]+)*@{1}[a-zA-Z]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$";
    public static final String REGEX_PHONE = "^0\\d{9}$";

    public static final String SECRET_KEY = "m4Fq0@#";
    public static final long ACCESS_TOKEN_TIME = 24L * 60L * 60 * 1000;
    public static final long REFRESH_TOKEN_TIME = 30L * 24 * 60 * 60 * 1000;
    public static final int MAX_FAILED_ATTEMPTS = 2;
    public static final int LOCK_TIME_DURATION = 3;    // 3 MINUTES
    public static final byte GENDER_TYPE_MIN = 0;
    public static final byte GENDER_TYPE_MAX = 3;

    public static final Double MIN_QUANTITY = 0D;
    public static final Double DOUBLE_SAME = 0.0001D;

    public static final String NO_CONSIGNMENT = "";
    public static final String NO_CONSIGNMENT_MESSAGE = "Không có lô";

    public static final String NO_CHECK = "";

    public static final int DECIMAL_DIGITS_DEFAULT = 4;

    public static final int ENTERPRISE_CODE_LENGTH = 4;
}
