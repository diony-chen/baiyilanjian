package com.diony.shopping.user.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diony.shopping.common.domain.User;
import com.diony.shopping.common.service.RedisService;
import com.diony.shopping.user.entity.UmsAdmin;
import com.diony.shopping.user.entity.UmsRole;
import com.diony.shopping.user.mapper.UmsAdminMapper;
import com.diony.shopping.user.service.UmsRoleService;
import com.diony.shopping.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ThinkPad
 * @version 1.0
 * @description: TODO
 * @date 2021/4/1 11:47
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RedisService redisService;

    @Autowired
    UmsRoleService umsRoleService;

    @Autowired
    UmsAdminMapper umsAdminMapper;

    @Override
    public User queryUser(String username) {
        log.info("RESOURCE_ROLES_MAP={}", redisService.hasKey("AUTH:RESOURCE_ROLES_MAP").toString());
        //获取用户信息
        UmsAdmin admin = queryAdminByUsername(username);
        if (admin != null) {
            List<UmsRole> roleList = umsRoleService.queryRoleList(admin.getId());
            User user = new User();
            BeanUtils.copyProperties(admin, user);
            if(CollUtil.isNotEmpty(roleList)){
                List<String> roleStrList = roleList.stream().map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
                user.setRoles(roleStrList);
            }
            return user;
        }
        return null;
    }

    public UmsAdmin queryAdminByUsername(String username){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        return umsAdminMapper.selectOne(queryWrapper);
    }
}
