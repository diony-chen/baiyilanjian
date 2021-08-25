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

    @ApiModelProperty(value = "是否下一页")
    private Boolean hasNext;

    /**
     * 分页
     * @param list        列表数据
     * @param total  总记录数
     * @param pageSize    每页记录数
     * @param pageNo    当前页数
     */
    public PageRes(List<T> list, Long total, Long pageSize, Long pageNo, Long totalPage) {
        this.list = list;
        this.total = total;
        this.pageSize = pageSize.intValue();
        this.pageNo = pageNo.intValue();
        this.totalPage = totalPage;
        this.hasNext = this.pageNo >= this.totalPage ? false : true;
    }
}
