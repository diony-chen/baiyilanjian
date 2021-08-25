package com.diony.shopping.admin.service.impl;

import com.diony.shopping.admin.entity.AdminLoginLog;
import com.diony.shopping.admin.mapper.AdminLoginLogMapper;
import com.diony.shopping.admin.service.AdminLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户登录日志表 服务实现类
 * </p>
 *
 * @author diony_chen
 * @since 2021-06-17
 */
@Service
public class AdminLoginLogServiceImpl extends ServiceImpl<AdminLoginLogMapper, AdminLoginLog> implements AdminLoginLogService {

}
