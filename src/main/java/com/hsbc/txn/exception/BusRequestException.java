package com.hsbc.txn.exception;

/**
 * 参数异常
 */
public class BusRequestException  extends RuntimeException{

    private Integer status;
    public BusRequestException(Integer status,String message) {
        super(message);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
