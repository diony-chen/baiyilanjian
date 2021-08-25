package com.diony.shopping.user.controller;


import com.diony.shopping.common.res.PageRes;
import com.diony.shopping.user.component.DynamicSecurityMetadataSource;
import com.diony.shopping.user.entity.SysResource;
import com.diony.shopping.user.res.BaseRes;
import com.diony.shopping.user.service.SysResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台资源表 前端控制器
 * </p>
 *
 * @author diony_chen
 * @since 2021-07-21
 */
@RestController
@RequestMapping("/console/resource")
public class SysResourceController {

    @Autowired
    private SysResourceService resourceService;
//    @Autowired
//    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation("添加后台资源")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes add(@RequestBody SysResource sysResource) {
        boolean bool = resourceService.save(sysResource);
//        dynamicSecurityMetadataSource.clearDataSource();
        if (bool) {
            return BaseRes.success(true);
        } else {
            return BaseRes.failed();
        }
    }

    @ApiOperation("修改后台资源")
    @RequestMapping(value = "/update/{resourceId}", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes update(@PathVariable Long resourceId,
                               @RequestBody SysResource sysResource) {
        sysResource.setId(resourceId);
        boolean bool = resourceService.updateById(sysResource);
//        dynamicSecurityMetadataSource.clearDataSource();
        if (bool) {
            return BaseRes.success(true);
        } else {
            return BaseRes.failed();
        }
    }

    @ApiOperation("根据ID获取资源详情")
    @RequestMapping(value = "/{resourceId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes<SysResource> queryByResourceId(@PathVariable Long resourceId) {
        SysResource sysResource = resourceService.getById(resourceId);
        return BaseRes.success(sysResource);
    }

    @ApiOperation("根据ID删除后台资源")
    @RequestMapping(value = "/delete/{resourceId}", method = RequestMethod.POST)
    @ResponseBody
    public BaseRes delete(@PathVariable Long resourceId) {
        boolean bool = resourceService.removeById(resourceId);
//        dynamicSecurityMetadataSource.clearDataSource();
        if (bool) {
            return BaseRes.success(true);
        } else {
            return BaseRes.failed();
        }
    }

    @ApiOperation("分页模糊查询后台资源")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes<PageRes<SysResource>> list(@RequestParam(required = false) Long categoryId,
                                              @RequestParam(required = false) String nameKeyword,
                                              @RequestParam(required = false) String urlKeyword,
                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageRes<SysResource> pageRes = resourceService.queryResourceList(categoryId, nameKeyword, urlKeyword, pageSize, pageNum);
        return BaseRes.success(pageRes);
    }

    @ApiOperation("查询所有后台资源")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes<List<SysResource>> listAll() {
        List<SysResource> resourceList = resourceService.list();
        return BaseRes.success(resourceList);
    }
}

