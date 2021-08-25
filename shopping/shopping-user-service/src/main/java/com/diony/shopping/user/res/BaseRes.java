package com.diony.shopping.user.res;


import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.diony.shopping.user.enums.ErrorCodeEnum;

/**
 * 公共返回参数
 * @param <T>
 */
public class BaseRes<T> {
    /**
     * 状态码
     */
    private long code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据封装
     */
    private T data;

    protected BaseRes() {
    }

    protected BaseRes(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> BaseRes<T> success(T data) {
        return new BaseRes<T>(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> BaseRes<T> success(T data, String message) {
        return new BaseRes<T>(ErrorCodeEnum.SUCCESS.getCode(), message, data);
    }


    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> BaseRes<T> failed(IErrorCode errorCode,String message) {
        return new BaseRes<T>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> BaseRes<T> failed(String message) {
        return new BaseRes<T>(ErrorCodeEnum.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> BaseRes<T> failed() {
        return failed(ErrorCodeEnum.FAILED.getMessage());
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> BaseRes<T> validateFailed() {
        return failed(ErrorCodeEnum.VALIDATE_FAILED.getMessage());
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> BaseRes<T> validateFailed(String message) {
        return new BaseRes<T>(ErrorCodeEnum.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> BaseRes<T> unauthorized(T data) {
        return new BaseRes<T>(ErrorCodeEnum.UNAUTHORIZED.getCode(), ErrorCodeEnum.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> BaseRes<T> forbidden(T data) {
        return new BaseRes<T>(ErrorCodeEnum.FORBIDDEN.getCode(), ErrorCodeEnum.FORBIDDEN.getMessage(), data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
