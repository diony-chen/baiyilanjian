package com.diony.shopping.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diony.shopping.user.entity.UmsAdminPermissionRelation;
import com.diony.shopping.user.mapper.UmsAdminPermissionRelationMapper;
import com.diony.shopping.user.service.UmsAdminPermissionRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) 服务实现类
 * </p>
 *
 * @author diony_chen
 * @since 2021-04-01
 */
@Service
public class UmsAdminPermissionRelationServiceImpl extends ServiceImpl<UmsAdminPermissionRelationMapper, UmsAdminPermissionRelation> implements UmsAdminPermissionRelationService {

}
