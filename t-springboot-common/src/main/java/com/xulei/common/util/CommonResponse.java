package com.xulei.common.util;

public class CommonResponse {
    public static <T> Response<T> error(String msg) {
        return new Response<T>(ResponseCode.FAIL, msg, null);
    }

    public static <T> Response<T> errorWithData(String msg, T data) {
        return new Response<T>(ResponseCode.FAIL, msg, data);
    }

    public static <T> Response<T> success() {
        return new Response<T>(ResponseCode.SUCCESS, "success", null);
    }

    public static <T> Response<T> successWithData(T data) {
        return new Response<T>(ResponseCode.SUCCESS, "success", data);
    }
}
