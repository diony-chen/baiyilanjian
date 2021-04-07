package com.diony.shopping.common.aop;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.diony.shopping.common.domain.WebLog;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponseWrapper;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author diony_chen
 * @version 1.0
 * @description: Controller 统一日志处理切面
 * @date 2021/3/31 16:13
 */
@Component
@Aspect
@Slf4j
public class ControllerLogAspect {
    @Pointcut("execution(public * com.diony.shopping.*.controller.*.*(..))")
    public void aspect() {
    }

    @Around("aspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求信息(通过Logstash传入Elasticsearch)
        WebLog webLog = new WebLog();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            webLog.setDescription(log.value());
        }

        String urlStr = request.getRequestURL().toString();
        webLog.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
        webLog.setIp(request.getServerName());
        webLog.setMethod(joinPoint.getSignature().toString());
        webLog.setParameter(getParameter(joinPoint));
        webLog.setStartTime(startTime);
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(request.getRequestURL().toString());

        log.info(" ");
        log.info("--------->IP:{}", webLog.getIp());
        log.info("--------->URL:{}", webLog.getUrl());
        log.info("--------->方法:{}", webLog.getMethod());
        log.info("--------->入参:{}", webLog.getParameter());
        Object result = joinPoint.proceed();
        webLog.setResult(result);
        log.info(" ");
        log.info("--------->出参:{}", webLog.getResult());
        log.info("--------->结束:耗时{}ms", (System.currentTimeMillis() - startTime));
        log.info(" ");

        return result;
    }

    public Object getParameter(JoinPoint joinPoint) {
        // 参数名称数组
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        // 参数值数组
        Object[] objs = joinPoint.getArgs();
        Map<String, Object> paramMap = new HashMap();
        if(StringUtils.isEmpty(objs)) {
            return paramMap;
        }

        for (int i = 0; i < objs.length; i++) {
            if (!(objs[i] instanceof ExtendedServletRequestDataBinder) && !(objs[i] instanceof HttpServletResponseWrapper)) {
                // 名称和值内容一一对应
                paramMap.put(argNames[i], objs[i]);
            }
        }
        return paramMap;
    }

}
