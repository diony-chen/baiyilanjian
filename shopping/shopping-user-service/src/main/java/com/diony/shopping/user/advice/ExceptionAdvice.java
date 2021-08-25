package com.diony.shopping.user.advice;


import com.diony.shopping.user.enums.ErrorCodeEnum;
import com.diony.shopping.user.exception.BaseException;
import com.diony.shopping.user.res.ExceptionBaseRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;


@RestControllerAdvice
public class ExceptionAdvice {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
     * 处理请求对象属性不满足校验规则的异常信息
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ExceptionBaseRes exception(HttpServletRequest request, MethodArgumentNotValidException exception) {
        logger.info("数据校验异常");
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();

        if(!CollectionUtils.isEmpty(fieldErrors)){
            builder.append(fieldErrors.get(0).getField()+fieldErrors.get(0).getDefaultMessage()+"!");
        }
        logger.info(Arrays.toString(fieldErrors.toArray()));
        return ExceptionBaseRes.create(ErrorCodeEnum.FAILED.getCode(), ErrorCodeEnum.FAILED.getMessage());
    }

    /**
     * http请求方法不支持
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ExceptionBaseRes exception(HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        String result = exception.getMessage();
        logger.warn(result);
        return ExceptionBaseRes.create(ErrorCodeEnum.FAILED.getCode(), ErrorCodeEnum.FAILED.getMessage());
    }


    /**
     * json错误
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ExceptionBaseRes exception(HttpServletRequest request, HttpMessageNotReadableException exception) {
        String result = exception.getMessage();
        logger.warn(result);
        return ExceptionBaseRes.create(ErrorCodeEnum.FAILED.getCode(), ErrorCodeEnum.FAILED.getMessage());
    }

    /**
     * 统一处理业务异常
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BaseException.class)
    public ExceptionBaseRes exception(HttpServletRequest request, BaseException exception) {
        logger.error("业务异常", exception);

        return ExceptionBaseRes.create(exception.getCode(), exception.getMessage());
    }


    /**
     * 数据库异常
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = DataAccessException.class)
    public ExceptionBaseRes exception(HttpServletRequest request, DataAccessException exception) {
        logger.error("数据库异常", exception);
        return ExceptionBaseRes.create(ErrorCodeEnum.FAILED.getCode(), ErrorCodeEnum.FAILED.getMessage());
    }

    /**
     * 统一处理异常
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ExceptionBaseRes exception(HttpServletRequest request, Exception exception) {
        logger.error("系统异常", exception);
        return ExceptionBaseRes.create(ErrorCodeEnum.FAILED.getCode(), ErrorCodeEnum.FAILED.getMessage());
    }

}
