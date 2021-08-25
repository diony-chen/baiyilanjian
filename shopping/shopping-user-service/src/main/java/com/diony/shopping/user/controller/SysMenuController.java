package com.diony.shopping.user.controller;


import com.diony.shopping.common.res.PageRes;
import com.diony.shopping.user.entity.SysMenu;
import com.diony.shopping.user.res.BaseRes;
import com.diony.shopping.user.res.SysMenuNodeRes;
import com.diony.shopping.user.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台菜单管理
 *
 * @author diony_chen
 * @since 2021-07-21
 */
@RestController
@Api(tags = "SysMenuController", value = "后台菜单管理")
@RequestMapping("/console/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;

    @ApiOperation("添加后台菜单")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes add(@RequestBody SysMenu sysMenu) {
        boolean bool = menuService.save(sysMenu);
        if (bool) {
            return BaseRes.success(true);
        } else {
            return BaseRes.failed();
        }
    }

    @ApiOperation("修改后台菜单")
    @RequestMapping(value = "/update/{menuId}", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes update(@PathVariable Long menuId,
                               @RequestBody SysMenu sysMenu) {
        sysMenu.setId(menuId);
        boolean bool = menuService.updateById(sysMenu);
        if (bool) {
            return BaseRes.success(true);
        } else {
            return BaseRes.failed();
        }
    }

    @ApiOperation("根据ID获取菜单详情")
    @RequestMapping(value = "/{menuId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes<SysMenu> getItem(@PathVariable Long menuId) {
        SysMenu sysMenu = menuService.getById(menuId);
        return BaseRes.success(sysMenu);
    }

    @ApiOperation("根据ID删除后台菜单")
    @RequestMapping(value = "/delete/{menuId}", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes delete(@PathVariable Long menuId) {
        boolean bool = menuService.removeById(menuId);
        if (bool) {
            return BaseRes.success(true);
        } else {
            return BaseRes.failed();
        }
    }

    @ApiOperation("分页查询后台菜单")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes<PageRes<SysMenu>> list(@PathVariable Long parentId,
                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageRes<SysMenu> pageRes = menuService.queryMenuList(parentId, pageSize, pageNum);
        return BaseRes.success(pageRes);
    }

    @ApiOperation("树形结构返回所有菜单列表")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes<List<SysMenuNodeRes>> treeList() {
        List<SysMenuNodeRes> list = menuService.treeList();
        return BaseRes.success(list);
    }

    @ApiOperation("修改菜单显示状态")
    @RequestMapping(value = "/updateHidden/{id}", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes updateHidden(@PathVariable Long id, @RequestParam("hidden") Integer hidden) {
        boolean bool = menuService.updateHidden(id, hidden);
        if (bool) {
            return BaseRes.success(true);
        } else {
            return BaseRes.failed();
        }
    }
}

