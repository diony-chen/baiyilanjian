package com.diony.shopping.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diony.shopping.common.res.PageRes;
import com.diony.shopping.user.entity.*;
import com.diony.shopping.user.mapper.SysRoleMapper;
import com.diony.shopping.user.service.SysRoleMenuRelationService;
import com.diony.shopping.user.service.SysRoleService;
import com.diony.shopping.user.service.SysUserRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author diony_chen
 * @since 2021-07-21
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
   private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleRelationService sysUserRoleRelationService;

    @Override
    public List<SysRole> queryRoleListByUserId(Long userId) {
        return sysRoleMapper.queryRoleListByUserId(userId);
    }

    @Override
    public PageRes<SysRole> queryRoleList(String keyword, Integer pageSize, Integer pageNo) {
        IPage iPage = new Page();
        iPage.setSize(pageSize);
        iPage.setCurrent(pageNo);
        IPage<SysRole> pageRes = sysRoleMapper.queryRoleList(iPage, keyword);
        return new PageRes(
                pageRes.getRecords(),
                iPage.getTotal(),
                iPage.getSize(),
                iPage.getCurrent(),
                iPage.getPages());
    }

    /**
     * 修改指定用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByUserId(Long userId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        QueryWrapper<SysUserRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        sysUserRoleRelationService.remove(queryWrapper);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<SysUserRoleRelation> list = new ArrayList<>();
            roleIds.forEach(roleId -> {
                SysUserRoleRelation roleRelation = new SysUserRoleRelation();
                roleRelation.setUserId(userId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            });
            sysUserRoleRelationService.saveBatch(list);
        }
//        adminCacheService.delResourceList(adminId);
        return count;
    }
}
