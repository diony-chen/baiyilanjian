package com.diony.shopping.user.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.diony.shopping.common.res.PageRes;
import com.diony.shopping.user.entity.SysRole;
import com.diony.shopping.user.entity.SysUser;
import com.diony.shopping.user.entity.SysUserDetails;
import com.diony.shopping.user.req.UserLoginReq;
import com.diony.shopping.user.req.UserReq;
import com.diony.shopping.user.res.BaseRes;
import com.diony.shopping.user.res.SysUserRes;
import com.diony.shopping.user.service.SysMenuService;
import com.diony.shopping.user.service.SysRoleService;
import com.diony.shopping.user.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 后台用户管理
 *
 * @author diony_chen
 * @since 2021-07-21
 */
@Slf4j
@Api(tags = "SysUserController", value = "后台用户管理")
@RestController
@RequestMapping("/console/user")
public class SysUserController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "登录返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes login(@Validated @RequestBody UserLoginReq userLoginReq) {
        log.info("登录 password={}", userLoginReq.getPassword());
        String token = sysUserService.login(userLoginReq.getUsername(), userLoginReq.getPassword());
        if (token == null) {
            return BaseRes.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return BaseRes.success(tokenMap);
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = sysUserService.refreshToken(token);
        if (refreshToken == null) {
            return BaseRes.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return BaseRes.success(tokenMap);
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes<SysUserRes> register(@Validated @RequestBody UserReq userReq) {
        SysUserRes sysUser = sysUserService.register(userReq);
        if (sysUser == null) {
            return BaseRes.failed();
        }

        return BaseRes.success(sysUser);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes info() {
        // 获取用户认证信息。
        Authentication getAuthentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("getAuthentication={}", JSONUtil.toJsonStr(getAuthentication));
        SysUserDetails principal = (SysUserDetails) getAuthentication.getPrincipal();
        if(principal == null){
            return BaseRes.unauthorized(null);
        }

        String username = principal.getUsername();
        SysUser sysUser = sysUserService.queryUserByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", sysUser.getUsername());
        data.put("menus", sysMenuService.queryMenuListByUserId(sysUser.getId()));
        data.put("icon", sysUser.getIcon());
        List<SysRole> roleList = sysRoleService.queryRoleListByUserId(sysUser.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(SysRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return BaseRes.success(data);
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes loginOut() {
        return BaseRes.success(null);
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes<PageRes<SysUserRes>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageRes<SysUserRes> adminList = sysUserService.queryUserList(keyword, pageSize, pageNum);
        return BaseRes.success(adminList);
    }

    @ApiOperation("获取指定用户信息")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes<SysUser> queryUserByUserId(@PathVariable Long userId) {
        SysUser admin = sysUserService.getById(userId);
        return BaseRes.success(admin);
    }

    @ApiOperation("修改指定用户信息")
    @RequestMapping(value = "/update/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes update(@PathVariable Long userId, @RequestBody SysUser sysUser) {
        boolean bool = sysUserService.update(userId, sysUser);
        if (bool) {
            return BaseRes.success(bool);
        }
        return BaseRes.failed();
    }

//    @ApiOperation("修改指定用户密码")
//    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
//    @ResponseBody
//    public BaseRes updatePassword(@Validated @RequestBody UpdateAdminPasswordParam updatePasswordParam) {
//        int status = sysUserService.updatePassword(updatePasswordParam);
//        if (status > 0) {
//            return BaseRes.success(status);
//        } else if (status == -1) {
//            return BaseRes.failed("提交参数不合法");
//        } else if (status == -2) {
//            return BaseRes.failed("找不到该用户");
//        } else if (status == -3) {
//            return BaseRes.failed("旧密码错误");
//        } else {
//            return BaseRes.failed();
//        }
//    }

    @ApiOperation("删除指定用户信息")
    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes delete(@PathVariable Long userId) {
        int count = sysUserService.deleteByUserId(userId);
        if (count > 0) {
            return BaseRes.success(count);
        }
        return BaseRes.failed();
    }

    @ApiOperation("给用户分配角色")
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes updateRoleByUserId(@RequestParam("userId") Long userId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = sysRoleService.updateByUserId(userId, roleIds);
        if (count >= 0) {
            return BaseRes.success(count);
        }
        return BaseRes.failed();
    }

    @ApiOperation("获取指定用户的角色")
    @RequestMapping(value = "/queryRoleList/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes<List<SysRole>> queryRoleListByUserId(@PathVariable Long userId) {
        List<SysRole> roleList = sysRoleService.queryRoleListByUserId(userId);
        return BaseRes.success(roleList);
    }
}

