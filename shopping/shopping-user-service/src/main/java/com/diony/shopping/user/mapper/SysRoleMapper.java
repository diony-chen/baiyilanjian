package com.diony.shopping.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.diony.shopping.common.res.PageRes;
import com.diony.shopping.user.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author diony_chen
 * @since 2021-07-21
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> queryRoleListByUserId(Long userId);

    IPage<SysRole> queryRoleList(IPage iPage, String keyword);
}
