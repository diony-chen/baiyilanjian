package com.diony.shopping.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.diony.shopping.user.entity.UmsRole;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author diony_chen
 * @since 2021-04-01
 */
public interface UmsRoleService extends IService<UmsRole> {

    List<UmsRole> queryRoleList(Long adminId);
}
