package com.diony.shopping.user.controller;

import com.diony.shopping.user.entity.SysResourceCategory;
import com.diony.shopping.user.res.BaseRes;
import com.diony.shopping.user.service.SysResourceCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台资源分类管理
 *
 * @author diony_chen
 * @since 2021-07-21
 */
@RestController
@Api(tags = "UmsResourceCategoryController", value = "后台资源分类管理")
@RequestMapping("/console/resourceCategory")
public class SysResourceCategoryController {

    @Autowired
    private SysResourceCategoryService resourceCategoryService;

    @ApiOperation("查询所有后台资源分类")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes<List<SysResourceCategory>> list() {
        List<SysResourceCategory> resourceList = resourceCategoryService.list();
        return BaseRes.success(resourceList);
    }

    @ApiOperation("添加后台资源分类")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes add(@RequestBody SysResourceCategory sysResourceCategory) {
        boolean bool = resourceCategoryService.save(sysResourceCategory);
        if (bool) {
            return BaseRes.success(true);
        } else {
            return BaseRes.failed();
        }
    }

    @ApiOperation("修改后台资源分类")
    @RequestMapping(value = "/update/{resourceCategoryId}", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes update(@PathVariable Long resourceCategoryId,
                               @RequestBody SysResourceCategory sysResourceCategory) {
        sysResourceCategory.setId(resourceCategoryId);
        boolean bool = resourceCategoryService.updateById(sysResourceCategory);
        if (bool) {
            return BaseRes.success(true);
        } else {
            return BaseRes.failed();
        }
    }

    @ApiOperation("根据ID删除后台资源")
    @RequestMapping(value = "/delete/{resourceCategoryId}", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes delete(@PathVariable Long resourceCategoryId) {
        boolean bool = resourceCategoryService.removeById(resourceCategoryId);
        if (bool) {
            return BaseRes.success(true);
        } else {
            return BaseRes.failed();
        }
    }
}

