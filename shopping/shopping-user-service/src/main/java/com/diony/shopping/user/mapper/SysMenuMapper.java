package com.diony.shopping.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.diony.shopping.user.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 后台菜单表 Mapper 接口
 * </p>
 *
 * @author diony_chen
 * @since 2021-07-21
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

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

    IPage<SysMenu> queryMenuList(IPage iPage, Long parentId);


}
