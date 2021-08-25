package com.diony.shopping.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.diony.shopping.common.res.PageRes;
import com.diony.shopping.user.entity.SysUser;
import com.diony.shopping.user.req.UserReq;
import com.diony.shopping.user.res.SysUserRes;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 后台用户管理 服务类
 *
 * @author diony_chen
 * @since 2021-07-21
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 用户注册
     * @param userReq 用户信息
     * @return
     */
    SysUserRes register(UserReq userReq);

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return
     */
    SysUser queryUserByUsername(String username);

    /**
     * 刷新Token
     * @param token token
     * @return
     */
    String refreshToken(String token);

    /**
     * 根据ID删除用户
     * @param userId 用户ID
     * @return
     */
    int deleteByUserId(Long userId);

    /**
     * 查询用户列表
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    PageRes<SysUserRes> queryUserList(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改用户信息
     * @param userId 用户ID
     * @param sysUser 用户信息
     * @return
     */
    boolean update(Long userId, SysUser sysUser);

}
