package com.lx.project;

public class BizException extends RuntimeException {

    public static String UNKNOWN_ERROR = "未知错误，请稍后刷新重试";

    public BizException() {
        super(UNKNOWN_ERROR);
    }

    public BizException(String message) {
        super(message);
    }
}
