package com.hsbc.txn.exception;

public enum TxnExceptionType {

    TXN_NOT_FOUND(10001, "Txn not found"),
    TXN_ALREADY_EXISTS(10002, "Txn already exists"),

    INVALID_ARGS(10003, "Invalid arguments"),

    UNKOWN_ERROR(99999, "Unknown error");

    private final Integer code;
    private final String message;

    TxnExceptionType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
