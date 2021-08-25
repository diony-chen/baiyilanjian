package com.diony.shopping.user.service.impl;

import com.diony.shopping.user.entity.SysPermission;
import com.diony.shopping.user.entity.SysResource;
import com.diony.shopping.user.entity.SysUser;
import com.diony.shopping.user.entity.SysUserDetails;
import com.diony.shopping.user.service.SysResourceService;
import com.diony.shopping.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息
 *
 * @author diony_chen
 * @since 2021-07-21
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysResourceService sysResourceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.queryUserByUsername(username);

        if (sysUser != null) {
            List<SysResource> permissionList = sysResourceService.queryResourceListByUserId(sysUser.getId());
            return new SysUserDetails(sysUser, permissionList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");

    }
}