package com.diony.shopping.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.diony.shopping.user.entity.SysResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台资源表 Mapper 接口
 * </p>
 *
 * @author diony_chen
 * @since 2021-07-21
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    /**
     * 获取用户所有可访问资源
     * @param userId
     * @return
     */
    List<SysResource> queryResourceListByUserId(@Param("userId") Long userId);

    IPage<SysResource> queryResourceList(IPage iPage, Long categoryId, String nameKeyword, String urlKeyword);

    List<SysResource> queryResourceListByRoleId(@Param("roleId") Long roleId);
}
