package com.diony.shopping.user.res;

import com.diony.shopping.user.entity.SysMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 后台菜单节点封装
 * Created by macro on 2020/2/4.
 */
@Getter
@Setter
public class SysMenuNodeRes extends SysMenu {

    @ApiModelProperty(value = "子级菜单")
    private List<SysMenuNodeRes> children;

}
