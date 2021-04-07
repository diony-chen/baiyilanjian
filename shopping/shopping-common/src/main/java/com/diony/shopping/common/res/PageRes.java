package com.diony.shopping.common.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 分页出参
 * @author diony_chen
 * @date 2021/3/30 14:29
 * @version 1.0
 */
@Data
public class PageRes<T> {

    @ApiModelProperty(value = "分页大小")
    protected Integer pageSize;

    @ApiModelProperty(value = "当前页")
    protected Integer pageNo;

    @ApiModelProperty(value = "总返回数")
    protected Long total;

    @ApiModelProperty(value = "总页数")
    protected Long totalPage;

    @ApiModelProperty(value = "返回参数")
    private List<T> list;

}
