package com.diony.shopping.user.exception;

import lombok.Getter;


@Getter
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 返回编码
     * <p>
     * 默认 400
     */
    private Integer code = 400;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}