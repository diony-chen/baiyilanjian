package com.diony.shopping.user.res;


import com.diony.shopping.user.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.ToString;
import org.slf4j.MDC;

import java.io.Serializable;

@ToString
@Data
public class ExceptionBaseRes<T> implements Serializable {

    private static final long serialVersionUID = 4410321644198111925L;

    private Integer code;

    private String message;

    private T data;

    private String traceId;

    public ExceptionBaseRes(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.traceId = MDC.get("traceId");
    }

    public ExceptionBaseRes(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.traceId = MDC.get("traceId");
    }

    public static <T> ExceptionBaseRes<T> create(T t, Integer code, String msg) {
        return new ExceptionBaseRes(code, msg, t);
    }

    public static <T> ExceptionBaseRes<T> create(Integer code, String msg) {
        return new ExceptionBaseRes(code, msg);
    }
}
