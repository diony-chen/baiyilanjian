package com.diony.shopping.user.config;

import cn.hutool.json.JSONUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


/**
 * @author diony_chen
 * @version 1.0
 * @description: 结果拦截器 〈功能详细描述〉
 * @date 2021/4/6 13:59
 */
@Slf4j
@NoArgsConstructor
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
        Object.class, RowBounds.class, ResultHandler.class})})
public class ResultSqlInterceptor implements Interceptor {

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 执行请求方法，并将所得结果保存到result中
        Object result = invocation.proceed();
        long endTime = System.currentTimeMillis();
        String str = JSONUtil.toJsonStr(result);
        log.info("-------------->mybatis 出参:" + str);
        log.info("-------------->mybatis 执行时间: " + (endTime - startTime) + " ms");
        return result;
    }

}