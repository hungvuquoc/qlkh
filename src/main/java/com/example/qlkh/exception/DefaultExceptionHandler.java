package com.example.qlkh.exception;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.qlkh.error.DataError;
import com.example.qlkh.error.status.CommonStatus;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PersistentObjectException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {
    @ExceptionHandler({Exception.class, UnsupportedOperationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected DataError<Object> handleException(Exception ex) {
        log.error("Handle Exception: errorMessage = {}", ex.getMessage(), ex);
        return DataError.build(CommonStatus.INTERNAL_SERVER_ERROR.getCode(), ex.getMessage());
    }

    @ExceptionHandler({DataException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected DataError<Object> handleDataException(DataException ex) {
        log.error("Handle DataException. code = {}, message = {}", ex.getCode(), ex.getMessage(), ex);
        return DataError.build(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler({DataRuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected DataError<Object> handleDataRuntimeException(DataRuntimeException ex) {
        if (ex.getDetail() == null)
            log.error("Handle DataRuntimeException. code = {}, message = {}", ex.getCode(), ex.getMessage(), ex);
        else
            log.error("Handle DataRuntimeException. code = {}, message = {}, detail = {}",
                    ex.getCode(),
                    ex.getMessage(),
                    ex.getDetail().getMessage(), ex);
        return DataError.build(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler({IOException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected DataError<Object> handleIOException(IOException ex) {
        log.error("Handle IOException. code = {}, message = {}",
                CommonStatus.INPUT_OUTPUT_FILE_ERROR.getCode(),
                ex.getMessage(), ex);
        return DataError.build(CommonStatus.INPUT_OUTPUT_FILE_ERROR);
    }

    @ExceptionHandler({FileNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected DataError<Object> handleFileNotFoundException(FileNotFoundException ex) {
        log.error("Handle FileNotFoundException. code = {}, message = {}",
                CommonStatus.FIND_FILE_ERROR.getCode(),
                ex.getMessage(), ex);
        return DataError.build(CommonStatus.FIND_FILE_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected DataError<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        log.error("Handle MethodArgumentNotValidException. code = {}, message = {}",
                CommonStatus.WRONG_FORMAT.getCode(),
                ex.getMessage(), ex);
        String message = (error != null) ? error.getDefaultMessage() : null;
        return DataError.build(CommonStatus.WRONG_FORMAT.getCode(), message);
    }

    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected DataError<Object> handleMethodArgumentNotValidException(ValidationException ex) {
        log.error("Handle ValidationException. code = {}, message = {}",
                CommonStatus.WRONG_FORMAT.getCode(),
                ex.getMessage(), ex);
        String message = ex.getMessage();
        Pattern pattern = Pattern.compile(".*: (.*)$");
        Matcher matcher = pattern.matcher(message);
        if (matcher.matches())
            return DataError.build(CommonStatus.WRONG_FORMAT.getCode(), matcher.group(1));
        return DataError.build(CommonStatus.WRONG_FORMAT.getCode(), message);
    }

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected DataError<Object> handleNullPointerException(NullPointerException ex) {
        log.error("Handle NullPointerException. code = {}, message = {}",
                CommonStatus.INTERNAL_SERVER_ERROR.getCode(),
                ex.getMessage(), ex);
        return DataError.build(CommonStatus.INTERNAL_SERVER_ERROR.getCode(), CommonStatus.INTERNAL_SERVER_ERROR.getMessage());
    }

    @ExceptionHandler({
            TokenExpiredException.class,
            SignatureVerificationException.class,
            JWTDecodeException.class,
            JWTVerificationException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected DataError<Object> handleJWTDecodeException(JWTVerificationException ex) {
        log.error("Handle JWTDecodeException. code = {}, message = {}",
                CommonStatus.FORBIDDEN.getCode(),
                ex.getMessage(), ex);
        return DataError.build(CommonStatus.FORBIDDEN);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public DataError<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        log.error("Handle EmptyResultDataAccessException. code = {}, message = {}",
                CommonStatus.NOT_FOUND.getCode(),
                ex.getMessage(), ex);
        return DataError.build(CommonStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public DataError<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error("Handle DataIntegrityViolationException. code = {}, message = {}",
                CommonStatus.DATA_INTEGRITY_VIOLATION.getCode(),
                ex.getMessage(), ex);
        return DataError.build(CommonStatus.DATA_INTEGRITY_VIOLATION);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public DataError<Object> handlePersistentObjectException(InvalidDataAccessApiUsageException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof PersistentObjectException)
            return handlePersistentObjectException((PersistentObjectException) cause);

        log.error("Handle InvalidDataAccessApiUsageException. code = {}, message = {}, ex = {}",
                CommonStatus.DATA_ACCESS_ERROR.getCode(),
                CommonStatus.DATA_ACCESS_ERROR.getMessage(),
                ex.getMessage(), ex);
        return DataError.build(CommonStatus.DATA_ACCESS_ERROR);
    }

    @ExceptionHandler(PersistentObjectException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public DataError<Object> handlePersistentObjectException(PersistentObjectException ex) {
        log.error("Handle PersistentObjectException. code = {}, message = {}, ex = {}",
                CommonStatus.OBJECT_ALREADY_EXIST.getCode(),
                CommonStatus.OBJECT_ALREADY_EXIST.getMessage(),
                ex.getMessage(), ex);
        return DataError.build(CommonStatus.OBJECT_ALREADY_EXIST);
    }
}