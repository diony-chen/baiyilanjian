package com.diony.shopping.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.diony.shopping.common.res.PageRes;
import com.diony.shopping.user.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author diony_chen
 * @since 2021-07-21
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 通过用户名查询用户列表
     * @param username
     * @return
     */
    List<SysUser> queryUserByUsername(@Param("username") String username);

    /**
     * 根据ID删除用户
     * @param userId
     * @return
     */
    int deleteByUserId(Long userId);

    /**
     * 查询用户列表
     * @param keyword
     * @param sysUserIPage
     * @return
     */
    IPage<SysUser> queryUserList(@Param("keyword") String keyword, IPage<SysUser> sysUserIPage);
}
