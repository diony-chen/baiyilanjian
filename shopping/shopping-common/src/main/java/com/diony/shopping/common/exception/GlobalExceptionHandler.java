package com.diony.shopping.common.exception;

import com.diony.shopping.common.res.BaseRes;
import com.diony.shopping.common.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 全局异常处理类
 * @author diony_chen
 * @date 2021/3/30 11:32
 * @version 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = ProcessException.class)
    public BaseRes handle(ProcessException e) {
        Integer code = e.getCode();
        String msg = e.getMsg();
        if (code != null) {
            log.error("code={}, msg={}", code, msg);
            return BaseRes.failed(code, msg);
        }
        return BaseRes.failed(code, msg);
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseRes handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return BaseRes.failed(ErrorEnum.PARAM_ERROR.getCode(), message);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public BaseRes handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return BaseRes.failed(ErrorEnum.BIN_ERROR.getCode(), message);
    }
}
