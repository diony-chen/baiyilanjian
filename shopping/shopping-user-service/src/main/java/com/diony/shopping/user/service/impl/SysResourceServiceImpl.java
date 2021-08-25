package com.diony.shopping.user.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diony.shopping.common.res.PageRes;
import com.diony.shopping.user.entity.SysResource;
import com.diony.shopping.user.entity.SysRoleMenuRelation;
import com.diony.shopping.user.entity.SysRoleResourceRelation;
import com.diony.shopping.user.mapper.SysResourceMapper;
import com.diony.shopping.user.service.SysResourceService;
import com.diony.shopping.user.service.SysRoleMenuRelationService;
import com.diony.shopping.user.service.SysRoleResourceRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台资源表 服务实现类
 * </p>
 *
 * @author diony_chen
 * @since 2021-07-21
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Autowired
    private SysRoleResourceRelationService sysRoleResourceRelationService;

    /**
     * 获取用户所有可访问资源
     * @param userId
     * @return
     */
    @Override
    public List<SysResource> queryResourceListByUserId(Long userId) {

        return sysResourceMapper.queryResourceListByUserId(userId);
    }

    /**
     * 获取角色资源列表
     *
     * @param roleId 角色ID
     * @return
     */
    @Override
    public List<SysResource> queryResourceListByRoleId(Long roleId) {
        return sysResourceMapper.queryResourceListByRoleId(roleId);
    }

    @Override
    public PageRes<SysResource> queryResourceList(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNo) {
        IPage iPage = new Page();
        iPage.setCurrent(pageNo);
        iPage.setSize(pageSize);
        IPage<SysResource> sysResourceIPage = sysResourceMapper.queryResourceList(iPage, categoryId, nameKeyword, urlKeyword);
        return new PageRes(
                sysResourceIPage.getRecords(),
                iPage.getTotal(),
                iPage.getSize(),
                iPage.getCurrent(),
                iPage.getPages());
    }

    /**
     * 修改角色资源
     *
     * @param roleId     角色ID
     * @param resourceIds 资源ID
     * @return
     */
    @Override
    public int updateByRoleId(Long roleId, List<Long> resourceIds) {
        //先删除原来的关系
        QueryWrapper<SysRoleResourceRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        sysRoleResourceRelationService.remove(queryWrapper);
        //批量插入新关系
        for (Long resourceId : resourceIds) {
            SysRoleResourceRelation sysRoleResourceRelation = new SysRoleResourceRelation();
            sysRoleResourceRelation.setRoleId(roleId);
            sysRoleResourceRelation.setResourceId(resourceId);
            sysRoleResourceRelationService.save(sysRoleResourceRelation);
        }
        return resourceIds.size();
    }

}
