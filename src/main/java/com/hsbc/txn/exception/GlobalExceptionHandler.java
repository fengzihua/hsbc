package com.hsbc.txn.exception;

import com.hsbc.txn.vo.ApiBaseResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static com.hsbc.txn.exception.TxnExceptionType.INVALID_ARGS;
import static com.hsbc.txn.exception.TxnExceptionType.UNKOWN_ERROR;

@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 处理入参异常
    @ExceptionHandler(BusRequestException.class)
    public ResponseEntity<?> handleCustomException(BusRequestException ex) {
        // 打印异常信息
        log.error("handleCustomException: {}", ex.getMessage());
        // 返回自定义异常信息和 400 状态码
        return new ResponseEntity<>(ApiBaseResponse.fail(ex.getStatus(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidException(MethodArgumentNotValidException ex) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> errors.put(e.getField(),e.getDefaultMessage()));
        // 打印异常信息
        log.error("MethodArgumentNotValidException: {}", errors);
        // 返回自定义异常信息和 400 状态码
        return new ResponseEntity<>(ApiBaseResponse.fail( INVALID_ARGS.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // 处理其他异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        // 打印异常信息
        log.error("handleGeneralException: {}", ex.getMessage());
        // 返回通用错误信息和 500 状态码
        return new ResponseEntity<>(ApiBaseResponse.fail(UNKOWN_ERROR.getCode(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
