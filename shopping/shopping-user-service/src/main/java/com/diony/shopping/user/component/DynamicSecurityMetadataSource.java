package com.diony.shopping.user.component;

import cn.hutool.core.util.URLUtil;
import com.diony.shopping.user.entity.SysResource;
import com.diony.shopping.user.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 初始化资源数据
     * @return
     */
    private Map<String, ConfigAttribute> loadDataSource() {
        List<SysResource> resourceList = sysResourceService.list();
        if(CollectionUtils.isEmpty(resourceList)) {
            return null;
        }
        return resourceList.stream().collect(
                Collectors.toMap(SysResource::getUrl,
                        resource -> new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()),
                        (key, value) -> value, ConcurrentHashMap::new));
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        Map<String, ConfigAttribute> stringConfigAttributeMap = loadDataSource();
        if(stringConfigAttributeMap == null){
            return null;
        }
        List<ConfigAttribute>  configAttributes = new ArrayList<>();
        //获取当前访问的路径
        String url = ((FilterInvocation) o).getRequestUrl();
        String path = URLUtil.getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();
        Iterator<String> iterator = stringConfigAttributeMap.keySet().iterator();
        //获取访问该路径所需资源
        while (iterator.hasNext()) {
            String pattern = iterator.next();
            if (pathMatcher.match(pattern, path)) {
                configAttributes.add(stringConfigAttributeMap.get(pattern));
            }
        }
        // 未设置操作请求权限，返回空集合
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
