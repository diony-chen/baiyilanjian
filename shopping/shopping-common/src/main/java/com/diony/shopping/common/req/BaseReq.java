package com.diony.shopping.common.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@ApiModel(value = "公共返回类")
public class BaseReq implements Serializable {

    private static final long serialVersionUID = 4192965340654225152L;

    @ApiModelProperty(value = "链路id")
    private String traceId;

}
