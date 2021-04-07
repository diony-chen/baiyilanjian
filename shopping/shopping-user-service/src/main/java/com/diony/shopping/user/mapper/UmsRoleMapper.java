package com.diony.shopping.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.diony.shopping.user.entity.UmsRole;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author diony_chen
 * @since 2021-04-01
 */
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    List<UmsRole> queryRoleList(Long adminId);

}
