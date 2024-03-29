package com.diony.shopping.user.component;


import cn.hutool.json.JSONUtil;
import com.diony.shopping.user.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录授权过滤器
 *
 * @author
 * @date 2021/8/3
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        // 从请求头中获取token ，传来的 token 结构 前置请求头 jwt数据 （中间有个空格）
        String authHeader = request.getHeader(this.tokenHeader);

        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            // The part after "Bearer "切割字符串，去除前置请求头，获取jwt的数据
            String authToken = authHeader.substring(this.tokenHead.length());
            // 从jwt数据中获取username
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            // 打印日志
            log.info("username={}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 通过用户名进行登录
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //验证token是否还有效
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    //获取用户 ，security里面的用户
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    //设置用户信息
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("userDetails={}", JSONUtil.toJsonStr(authentication.getPrincipal()));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
