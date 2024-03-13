package com.example.qlkh.exception;

import com.example.qlkh.error.status.ErrorStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataException extends Exception{
    private int code;
    private String message;

    public DataException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public DataException(int code, String format, Object... args) {
        this.code = code;
        this.message = String.format(format, args);
    }

    public DataException(ErrorStatus status){
        this.code = status.getCode();
        this.message = status.getMessage();
    }
}
