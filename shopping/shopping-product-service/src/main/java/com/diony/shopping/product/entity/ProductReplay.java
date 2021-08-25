package com.diony.shopping.product.entity;

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
@TableName("product_replay")
@ApiModel(value="ProductReplay对象", description="")
public class ProductReplay implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "评论id")
    private Long commentId;

    @ApiModelProperty(value = "会员昵称")
    private String memberNickName;

    @ApiModelProperty(value = "会员头像")
    private String memberIcon;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "评论人员类型；0->会员；1->管理员")
    private Integer type;

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
