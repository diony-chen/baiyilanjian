package com.diony.shopping.user.controller;


import com.diony.shopping.common.res.PageRes;
import com.diony.shopping.user.entity.SysMenu;
import com.diony.shopping.user.entity.SysResource;
import com.diony.shopping.user.entity.SysRole;
import com.diony.shopping.user.res.BaseRes;
import com.diony.shopping.user.service.SysMenuService;
import com.diony.shopping.user.service.SysResourceService;
import com.diony.shopping.user.service.SysRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 前端控制器
 * </p>
 *
 * @author diony_chen
 * @since 2021-07-21
 */
@RestController
@RequestMapping("/console/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysResourceService sysResourceService;


    @ApiOperation("添加角色")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes add(@RequestBody SysRole role) {
        boolean bool = sysRoleService.save(role);
        if (bool) {
            return BaseRes.success(true);
        }
        return BaseRes.failed();
    }

    @ApiOperation("修改角色")
    @RequestMapping(value = "/update/{roleId}", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes update(@PathVariable Long roleId, @RequestBody SysRole role) {
        role.setId(roleId);
        boolean bool = sysRoleService.updateById(role);
        if (bool) {
            return BaseRes.success(true);
        }
        return BaseRes.failed();
    }

    @ApiOperation("批量删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes delete(@RequestParam("ids") List<Long> ids) {
        boolean bool = sysRoleService.removeByIds(ids);
        if (bool) {
            return BaseRes.success(true);
        }
        return BaseRes.failed();
    }

    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes<List<SysRole>> listAll() {
        List<SysRole> roleList = sysRoleService.list();
        return BaseRes.success(roleList);
    }

    @ApiOperation("根据角色名称分页获取角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes<PageRes<SysRole>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageRes<SysRole> pageRes = sysRoleService.queryRoleList(keyword, pageSize, pageNum);
        return BaseRes.success(pageRes);
    }

    @ApiOperation("修改角色状态")
    @RequestMapping(value = "/updateStatus/{roleId}", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes updateStatus(@PathVariable Long roleId, @RequestParam(value = "status") Integer status) {
        SysRole sysRole = new SysRole();
        sysRole.setId(roleId);
        sysRole.setStatus(status);
        boolean bool = sysRoleService.updateById(sysRole);
        if (bool) {
            return BaseRes.success(true);
        }
        return BaseRes.failed();
    }

    @ApiOperation("获取角色菜单列表")
    @RequestMapping(value = "/queryMenuList/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes<List<SysMenu>> queryMenuList(@PathVariable Long roleId) {
        List<SysMenu> roleList = sysMenuService.queryMenuListByRoleId(roleId);
        return BaseRes.success(roleList);
    }

    @ApiOperation("获取角色相关资源")
    @RequestMapping(value = "/queryResourceList/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes<List<SysResource>> queryResourceList(@PathVariable Long roleId) {
        List<SysResource> roleList = sysResourceService.queryResourceListByRoleId(roleId);
        return BaseRes.success(roleList);
    }

    @ApiOperation("给角色分配菜单")
    @RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes updateMenuByRoleId(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        int count = sysMenuService.updateByRoleId(roleId, menuIds);
        return BaseRes.success(count);
    }

    @ApiOperation("给角色分配资源")
    @RequestMapping(value = "/updateResource", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes updateResourceByRoleId(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
        int count = sysResourceService.updateByRoleId(roleId, resourceIds);
        return BaseRes.success(count);
    }
}

