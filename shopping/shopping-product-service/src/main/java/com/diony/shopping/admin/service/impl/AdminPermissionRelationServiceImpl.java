package com.diony.shopping.admin.service.impl;

import com.diony.shopping.admin.entity.AdminPermissionRelation;
import com.diony.shopping.admin.mapper.AdminPermissionRelationMapper;
import com.diony.shopping.admin.service.AdminPermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) 服务实现类
 * </p>
 *
 * @author diony_chen
 * @since 2021-06-17
 */
@Service
public class AdminPermissionRelationServiceImpl extends ServiceImpl<AdminPermissionRelationMapper, AdminPermissionRelation> implements AdminPermissionRelationService {

}
