package com.hsbc.txn.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiBaseResponse<T> implements Serializable {
    private T data;
    private String message;
    private int status;

    public static <T> ApiBaseResponse<T> success(T data) {
        ApiBaseResponse<T> resp = new ApiBaseResponse<>();
        resp.status = 0;
        resp.data = data;
        resp.message = "success";
        return resp;
    }

    public static <T> ApiBaseResponse<T> fail(Integer status, String msg ) {
        ApiBaseResponse<T> resp = new ApiBaseResponse<>();
        resp.status = status;
        resp.data = null;
        resp.message = msg;
        return resp;
    }



}
