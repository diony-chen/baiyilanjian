package com.diony.shopping.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.diony.shopping.common.res.PageRes;
import com.diony.shopping.user.entity.SysMenu;
import com.diony.shopping.user.entity.SysResource;
import com.diony.shopping.user.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author diony_chen
 * @since 2021-07-21
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 获取用户角色列表
     * @param userId 用户ID
     * @return
     */
    List<SysRole> queryRoleListByUserId(Long userId);

    /**
     * 查询角色列表
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    PageRes<SysRole> queryRoleList(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改用户角色
     * @param userId 用户ID
     * @param roleIds 角色ID
     * @return
     */
    int updateByUserId(Long userId, List<Long> roleIds);

}
