package com.diony.shopping.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diony.shopping.user.entity.UmsRole;
import com.diony.shopping.user.mapper.UmsRoleMapper;
import com.diony.shopping.user.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author diony_chen
 * @since 2021-04-01
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements UmsRoleService {

    @Autowired
    UmsRoleMapper umsRoleMapper;

    @Override
    public List<UmsRole> queryRoleList(Long adminId) {
        return umsRoleMapper.queryRoleList(adminId);
    }
}
