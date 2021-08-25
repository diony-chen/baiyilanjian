package com.diony.shopping.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.diony.shopping.common.res.PageRes;
import com.diony.shopping.user.entity.SysResource;

import java.util.List;

/**
 * <p>
 * 后台资源表 服务类
 * </p>
 *
 * @author diony_chen
 * @since 2021-07-21
 */
public interface SysResourceService extends IService<SysResource> {

    /**
     * 获取用户资源列表
     * @param userId 用户ID
     * @return
     */
    List<SysResource> queryResourceListByUserId(Long userId);

    /**
     * 获取角色资源列表
     * @param roleId 角色ID
     * @return
     */
    List<SysResource> queryResourceListByRoleId(Long roleId);

    /**
     * 查询资源列表
     * @param categoryId
     * @param nameKeyword
     * @param urlKeyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    PageRes<SysResource> queryResourceList(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);


    /**
     * 修改角色资源
     * @param roleId 角色ID
     * @param resourceIds 资源ID列表
     * @return
     */
    int updateByRoleId(Long roleId, List<Long> resourceIds);
}
