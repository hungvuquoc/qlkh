package com.example.qlkh.exception;

import com.example.qlkh.error.status.ErrorStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataRuntimeException extends RuntimeException {
    private int code;
    private String message;
    private Exception detail;

    public DataRuntimeException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public DataRuntimeException(int code, String format, Object... args) {
        this.code = code;
        this.message = String.format(format, args);
    }

    public DataRuntimeException(ErrorStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public DataRuntimeException(ErrorStatus status, Exception detail) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.detail = detail;
    }
}
