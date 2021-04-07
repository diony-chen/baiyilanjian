package com.diony.shopping.common.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.slf4j.MDC;

import java.io.Serializable;

@Data
@ToString
@ApiModel(value = "公共返回类")
public class BaseRes<T extends Object> implements Serializable {

    private static final long serialVersionUID = 4192965340654225152L;

    private static final Integer SUCCESS = 200;

    @ApiModelProperty(value = "返回编码: 200成功")
    private Integer code;

    @ApiModelProperty(value = "返回描述")
    private String message;

    @ApiModelProperty(value = "返回参数")
    private T data;

    @ApiModelProperty(value = "当前时间戳")
    private Long timeStamp;

    @ApiModelProperty(value = "链路id")
    private String traceId;

    public BaseRes() {
        this.timeStamp = System.currentTimeMillis();
    }

    public BaseRes(T data) {
        this(SUCCESS, data);
    }

    public BaseRes(Integer code, T data) {
        this(code, null, data);
    }

    public BaseRes(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseRes(Integer code, String message, T data) {
        this(code, message, data, System.currentTimeMillis());
    }

    public BaseRes(Integer code, String message, T data, Long timeStamp) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timeStamp = timeStamp;
        this.traceId = MDC.get("traceId");
    }

    public static <T extends Object> BaseRes<T> success(T t) {
        return new BaseRes(t);
    }

    public static <T extends Object> BaseRes<T> success(T t, Integer code, String msg) {
        return new BaseRes(code, msg, t);
    }

    public static <T extends Object> BaseRes<T> failed(Integer code, String msg) {
        return new BaseRes(code, msg);
    }

}
