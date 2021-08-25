package com.diony.shopping.user.component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.diony.shopping.user.config.IgnoreUrlsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * 访问受保护的权限第一步
 * 
 * 访问受保护url时，会通过AbstractSecurityInterceptor拦截器拦截
 * 其中会调用FilterInvocationSecurityMetadataSource的方法来获取被拦截url所需的全部权限
 * 在调用授权管理器AccessDecisionManager，这个授权管理器会通过spring的全局缓存SecurityContextHolder获取用户的权限信息，
 * 还会获取被拦截的url和被拦截url所需的全部权限，然后根据所配的策略（有：一票决定，一票否定，少数服从多数等），如果权限足够，则返回，权限不够则报错并调用权限不足页面。
 * @author Administrator
 *
 */
@Service
public class DynamicSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

	@Autowired
	private FilterInvocationSecurityMetadataSource dynamicSecurityMetadataSource;

	@Autowired
	private IgnoreUrlsConfig ignoreUrlsConfig;
	
    @Autowired
    public void setSecurityMetadataSource(DynamicAccessDecisionManager dynamicAccessDecisionManager) {
        super.setAccessDecisionManager(dynamicAccessDecisionManager);
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	//登陆后，每次访问资源都通过这个拦截器拦截
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
    	FilterInvocation fi = new FilterInvocation(request, response, filterChain);
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		//OPTIONS请求直接放行
		if(httpServletRequest.getMethod().equals(HttpMethod.OPTIONS.toString())){
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
			return;
		}
		//白名单请求直接放行
		PathMatcher pathMatcher = new AntPathMatcher();
		for (String path : ignoreUrlsConfig.getUrls()) {
			if(pathMatcher.match(path, httpServletRequest.getRequestURI())){
				fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
				return;
			}
		}

		//before
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			//过滤器链
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		}
		finally {
			//finally
			super.finallyInvocation(token);
		}
		//after
		super.afterInvocation(token, null);
	}

	@Override
	public void destroy() {}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.dynamicSecurityMetadataSource;
	}

}