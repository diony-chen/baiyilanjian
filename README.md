# myBatis-plus-generator

## 简介

SpringBoot+MyBatis-Plus的快速开发脚手架。

## 技术选型

| 技术                   | 版本    | 说明             |
| ---------------------- | ------- | ---------------- |
| SpringBoot             | 2.3.9   | 容器+MVC框架     |
| MyBatis                | 3.5.4   | ORM框架          |
| MyBatis-Plus           | 3.3.2   | MyBatis增强工具  |
| MyBatis-Plus Generator | 3.3.2   | 数据层代码生成器 |
| Lombok                 | 1.18.12 | 简化对象封装工具 |

## 使用流程

### 环境搭建

简化依赖服务，只需安装最常用的MySql服务即可。

### 开发规约

#### 项目包结构

``` 
src
├── generator -- MyBatis-Plus代码生成器
├── modules -- 存放业务代码的基础包
|   └── ums -- 权限管理模块业务代码
|       ├── controller -- 该模块相关接口
|       ├── dto -- 该模块数据传输封装对象
|       ├── mapper -- 该模块相关Mapper接口
|       ├── model -- 该模块相关实体类
|       └── service -- 该模块相关业务处理类
```

#### 资源文件说明

``` 
resources
├── mapper -- MyBatis中mapper.xml存放位置
├── application.yml -- SpringBoot通用配置文件
├── application-dev.yml -- SpringBoot开发环境配置文件
├── application-prod.yml -- SpringBoot生产环境配置文件
└── generator.properties -- MyBatis-Plus代码生成器配置
```

#### 使用代码生成器

> 运行`MyBatisPlusGenerator`类的main方法来生成代码，可直接生成controller、service、mapper、model、mapper.xml的代码，无需手动创建。

- 代码生成器支持两种模式，一种生成单表的代码，比如只生成`pms_brand`表代码可以先输入`pms`，后输入`pms_brand`；

- 生成代码结构一览；

- 另一种直接生成整个模块的代码，比如生成`pms`模块代码可以先输入`pms`，后输入`pms_*`。

#### 编写业务代码

##### 单表查询

> 由于MyBatis-Plus提供的增强功能相当强大，单表查询几乎不用手写SQL，直接使用ServiceImpl和BaseMapper中提供的方法即可。

比如我们的菜单管理业务实现类`UmsMenuServiceImpl`中的方法都直接使用了这些方法。

```java
/**
 * 后台菜单管理Service实现类
 * Created by macro on 2020/2/2.
 */
@Service
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper,UmsMenu>implements UmsMenuService {

    @Override
    public boolean create(UmsMenu umsMenu) {
        umsMenu.setCreateTime(new Date());
        updateLevel(umsMenu);
        return save(umsMenu);
    }

    @Override
    public boolean update(Long id, UmsMenu umsMenu) {
        umsMenu.setId(id);
        updateLevel(umsMenu);
        return updateById(umsMenu);
    }

    @Override
    public Page<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
        Page<UmsMenu> page = new Page<>(pageNum,pageSize);
        QueryWrapper<UmsMenu> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMenu::getParentId,parentId)
                .orderByDesc(UmsMenu::getSort);
        return page(page,wrapper);
    }

    @Override
    public List<UmsMenuNode> treeList() {
        List<UmsMenu> menuList = list();
        List<UmsMenuNode> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
        return result;
    }

    @Override
    public boolean updateHidden(Long id, Integer hidden) {
        UmsMenu umsMenu = new UmsMenu();
        umsMenu.setId(id);
        umsMenu.setHidden(hidden);
        return updateById(umsMenu);
    }
}
```

##### 分页查询

> 对于分页查询MyBatis-Plus原生支持，不需要再整合其他插件，直接构造Page对象，然后调用ServiceImpl中的page方法即可。

```java
/**
 * 后台菜单管理Service实现类
 * Created by macro on 2020/2/2.
 */
@Service
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper,UmsMenu>implements UmsMenuService {
    @Override
    public Page<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
        Page<UmsMenu> page = new Page<>(pageNum,pageSize);
        QueryWrapper<UmsMenu> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMenu::getParentId,parentId)
                .orderByDesc(UmsMenu::getSort);
        return page(page,wrapper);
    }
}
```

##### 多表查询

> 对于多表查询，我们需要手写mapper.xml中的SQL实现，由于之前我们已经生成了mapper.xml文件，所以我们直接在Mapper接口中定义好方法，然后在mapper.xml写好SQL实现即可。

- 比如说我们需要写一个根据用户ID获取其分配的菜单的方法，首先我们在`UmsMenuMapper`接口中添加好`getMenuList`方法；

```java
/**
 * <p>
 * 后台菜单表 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2020-08-21
 */
public interface UmsMenuMapper extends BaseMapper<UmsMenu> {

    /**
     * 根据后台用户ID获取菜单
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);

}
```

- 然后在`UmsMenuMapper.xml`添加该方法的对应SQL实现即可。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.macro.mall.tiny.modules.ums.mapper.UmsMenuMapper">

    <select id="getMenuList" resultType="com.macro.mall.tiny.modules.ums.model.UmsMenu">
        SELECT
            m.id id,
            m.parent_id parentId,
            m.create_time createTime,
            m.title title,
            m.level level,
            m.sort sort,
            m.name name,
            m.icon icon,
            m.hidden hidden
        FROM
            ums_admin_role_relation arr
                LEFT JOIN ums_role r ON arr.role_id = r.id
                LEFT JOIN ums_role_menu_relation rmr ON r.id = rmr.role_id
                LEFT JOIN ums_menu m ON rmr.menu_id = m.id
        WHERE
            arr.admin_id = #{adminId}
          AND m.id IS NOT NULL
        GROUP BY
            m.id
    </select>
    
</mapper>
```

#### 请求参数校验

> 默认集成了`Jakarta Bean Validation`参数校验框架，只需在参数对象属性中添加`javax.validation.constraints`包中的注解注解即可实现校验功能，这里以登录参数校验为例。

- 首先在登录请求参数中添加`@NotEmpty`注解；

```java
/**
 * 用户登录参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminLoginParam {
    @NotEmpty
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
```

- 然后在登录接口中添加`@Validated`注解开启参数校验功能即可。

```java
/**
 * 后台用户管理
 * Created by macro on 2018/4/26.
 */
@Controller
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
}
```


