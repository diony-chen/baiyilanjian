package com.diony.shopping.common.exception;

import lombok.Data;

@Data
public class ProcessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    public ProcessException(Integer code, String message) {
        super(message);
        setCode(code);
    }

}
