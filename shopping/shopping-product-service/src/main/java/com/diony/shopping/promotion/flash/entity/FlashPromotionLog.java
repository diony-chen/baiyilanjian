package com.diony.shopping.flash.entity;

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
@TableName("flash_promotion_log")
@ApiModel(value="FlashPromotionLog对象", description="")
public class FlashPromotionLog implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "会员id")
    private Integer memberId;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "会员电话")
    private String memberPhone;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "会员订阅时间")
    private Date subscribeTime;

    @ApiModelProperty(value = "发送时间")
    private Date sendTime;

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
