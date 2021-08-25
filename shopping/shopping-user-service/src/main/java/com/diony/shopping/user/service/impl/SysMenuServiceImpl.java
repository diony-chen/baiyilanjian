package com.diony.shopping.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diony.shopping.common.res.PageRes;
import com.diony.shopping.user.entity.SysMenu;
import com.diony.shopping.user.entity.SysRoleMenuRelation;
import com.diony.shopping.user.mapper.SysMenuMapper;
import com.diony.shopping.user.res.SysMenuNodeRes;
import com.diony.shopping.user.service.SysMenuService;
import com.diony.shopping.user.service.SysRoleMenuRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author diony_chen
 * @since 2021-07-21
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuRelationService sysRoleMenuRelationService;

    /**
     * 根据管理员ID获取对应菜单
     *
     * @param userId
     */
    @Override
    public List<SysMenu> queryMenuListByUserId(Long userId) {
        return sysMenuMapper.queryMenuListByUserId(userId);
    }

    /**
     * 获取角色菜单列表
     *
     * @param roleId 角色ID
     * @return
     */
    @Override
    public List<SysMenu> queryMenuListByRoleId(Long roleId) {
        return sysMenuMapper.queryMenuListByRoleId(roleId);
    }

    /**
     * 查询菜单列表
     * @param parentId
     * @param pageSize
     * @param pageNo
     * @return
     */
    @Override
    public PageRes<SysMenu> queryMenuList(Long parentId, Integer pageSize, Integer pageNo) {
        IPage iPage = new Page();
        iPage.setCurrent(pageNo);
        iPage.setSize(pageSize);
        IPage<SysMenu> sysMenuIPage = sysMenuMapper.queryMenuList(iPage, parentId);
        return new PageRes(
                sysMenuIPage.getRecords(),
                iPage.getTotal(),
                iPage.getSize(),
                iPage.getCurrent(),
                iPage.getPages());
    }

    @Override
    public List<SysMenuNodeRes> treeList() {
        List<SysMenu> menuList = this.list();
        return menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
    }

    @Override
    public boolean updateHidden(Long id, Integer hidden) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(id);
        sysMenu.setHidden(hidden);
        return this.updateById(sysMenu);
    }

    /**
     * 修改角色菜单
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID
     * @return
     */
    @Override
    public int updateByRoleId(Long roleId, List<Long> menuIds) {
        //先删除原来的关系
        QueryWrapper<SysRoleMenuRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        sysRoleMenuRelationService.remove(queryWrapper);
        //批量插入新关系
        for (Long menuId : menuIds) {
            SysRoleMenuRelation sysRoleMenuRelation = new SysRoleMenuRelation();
            sysRoleMenuRelation.setRoleId(roleId);
            sysRoleMenuRelation.setMenuId(menuId);
            sysRoleMenuRelationService.save(sysRoleMenuRelation);
        }
        return menuIds.size();
    }

    /**
     * 将SysMenu转化为SysMenuNode并设置children属性
     * @param menu 菜单
     * @param menuList 菜单列表
     * @return
     */
    private SysMenuNodeRes covertMenuNode(SysMenu menu, List<SysMenu> menuList) {
        SysMenuNodeRes node = new SysMenuNodeRes();
        BeanUtils.copyProperties(menu, node);
        List<SysMenuNodeRes> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }


}
