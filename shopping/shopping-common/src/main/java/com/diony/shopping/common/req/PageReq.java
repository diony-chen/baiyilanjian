package com.diony.shopping.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @description: 分页入参
 * @author diony_chen
 * @date 2021/3/30 11:49
 * @version 1.0
 */
@Data
public class PageReq implements Serializable {

    @ApiModelProperty(value = "分页大小", required = false)
    @Min(value = 1, message = "分页大小不能小于1")
    private Integer pageSize = 20;

    @ApiModelProperty(value = "分页索引", required = false)
    @Min(value = 1, message = "页码不能小于1")
    private Integer pageNo = 1;

    @ApiModelProperty(value = "排序字段", required = false)
    private String sort;

    @ApiModelProperty(value = "是否降序", required = false)
    private boolean descend;

    public PageReq() {
    }

    public PageReq(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        if(pageNo == 0){
            pageNo = 1;
        }
        return pageNo;
    }

}
