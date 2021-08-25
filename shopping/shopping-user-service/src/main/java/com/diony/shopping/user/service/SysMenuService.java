package com.diony.shopping.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.diony.shopping.common.res.PageRes;
import com.diony.shopping.user.entity.SysMenu;
import com.diony.shopping.user.res.SysMenuNodeRes;

import java.util.List;

/**
 * <p>
 * 后台菜单表 服务类
 * </p>
 *
 * @author diony_chen
 * @since 2021-07-21
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取用户菜单列表
     * @param userId 用户ID
     * @return
     */
    List<SysMenu> queryMenuListByUserId(Long userId);

    /**
     * 获取角色菜单列表
     * @param roleId 角色ID
     * @return
     */
    List<SysMenu> queryMenuListByRoleId(Long roleId);

    /**
     * 查询菜单列表
     * @param parentId 父级ID
     * @param pageSize 分页条数
     * @param pageNum  第几页
     * @return
     */
    PageRes<SysMenu> queryMenuList(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 树形菜单列表
     * @return
     */
    List<SysMenuNodeRes> treeList();

    /**
     * 菜单隐藏
     * @param menuId 菜单ID
     * @param hidden 隐藏
     * @return
     */
    boolean updateHidden(Long menuId, Integer hidden);

    /**
     * 修改角色菜单
     * @param roleId 角色ID
     * @param menuIds 菜单ID
     * @return
     */
    int updateByRoleId(Long roleId, List<Long> menuIds);

}
