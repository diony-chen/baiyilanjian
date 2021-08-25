package com.diony.shopping.product.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author diony_chen
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_operate_log")
@ApiModel(value="ProductOperateLog对象", description="")
public class ProductOperateLog implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "改变前价格")
    private BigDecimal priceOld;

    @ApiModelProperty(value = "改变后价格")
    private BigDecimal priceNew;

    @ApiModelProperty(value = "改变前优惠价")
    private BigDecimal salePriceOld;

    @ApiModelProperty(value = "改变后优惠价")
    private BigDecimal salePriceNew;

    @ApiModelProperty(value = "改变前积分")
    private Integer giftPointOld;

    @ApiModelProperty(value = "改变后积分")
    private Integer giftPointNew;

    @ApiModelProperty(value = "改变前积分使用限制")
    private Integer usePointLimitOld;

    @ApiModelProperty(value = "改变后积分使用限制")
    private Integer usePointLimitNew;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "修改人")
    private String updatedBy;

    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;


}
