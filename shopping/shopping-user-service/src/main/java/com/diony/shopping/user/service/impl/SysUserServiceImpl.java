package com.diony.shopping.user.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diony.shopping.common.res.PageRes;
import com.diony.shopping.user.entity.SysUser;
import com.diony.shopping.user.exception.BaseException;
import com.diony.shopping.user.mapper.SysUserMapper;
import com.diony.shopping.user.req.UserReq;
import com.diony.shopping.user.res.SysUserRes;
import com.diony.shopping.user.service.SysResourceService;
import com.diony.shopping.user.service.SysUserService;
import com.diony.shopping.user.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.Asserts;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 后台用户管理 服务类
 *
 * @author diony_chen
 * @since 2021-07-21
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户登录获取Token
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String login(String username, String password) {
        String token = null;
        try {
            // 查询用户信息
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            log.info("password={}, passwordEncoder={}", userDetails.getPassword(), passwordEncoder.encode(password));
            Asserts.check(passwordEncoder.matches(password, userDetails.getPassword()),"密码不正确");
            Asserts.check(userDetails.isEnabled(),"帐号已被禁用");

            token = jwtTokenUtil.generateToken(userDetails);
        } catch (BaseException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 用户注册
     * @param userReq 用户信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUserRes register(UserReq userReq) {
        //查询是否有相同用户名的用户
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userReq.getUsername());
        List<SysUser> sysUserList = sysUserMapper.queryUserByUsername(userReq.getUsername());
        if (!CollectionUtils.isEmpty(sysUserList)) {
            return null;
        }

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userReq, sysUser);
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(userReq.getPassword());
        sysUser.setPassword(encodePassword);
        sysUserMapper.insert(sysUser);

        SysUserRes sysUserRes = new SysUserRes();
        BeanUtils.copyProperties(sysUser, sysUserRes);
        return sysUserRes;
    }

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUser queryUserByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (sysUser != null) {
            return sysUser;
        }
        return null;
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }

    /**
     * 删除指定用户
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByUserId(Long userId) {
        return sysUserMapper.deleteByUserId(userId);
    }

    /**
     * 查询用户分页列表
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageRes<SysUserRes> queryUserList(String keyword, Integer pageSize, Integer pageNum) {
        IPage<SysUser> sysUserIPage = new Page<>(pageNum, pageSize);
        IPage<SysUser> iPage = sysUserMapper.queryUserList(keyword, sysUserIPage);
        return new PageRes(
                iPage.getRecords(),
                iPage.getTotal(),
                iPage.getSize(),
                iPage.getCurrent(),
                iPage.getPages());
    }

    /**
     * 修改用户信息
     *
     * @param sysUser
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Long userId, SysUser sysUser) {
        sysUser.setId(userId);
        SysUser user = sysUserMapper.selectById(userId);
        if(user.getPassword().equals(sysUser.getPassword())){
            //与原加密密码相同的不需要修改
            sysUser.setPassword(null);
        }else{
            //与原加密密码不同的需要加密修改
            if(StrUtil.isEmpty(sysUser.getPassword())){
                sysUser.setPassword(null);
            }else{
                sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
            }
        }
        int count = sysUserMapper.updateById(sysUser);
        if(count > 0) {
            return true;
        }
        return false;
    }


}
