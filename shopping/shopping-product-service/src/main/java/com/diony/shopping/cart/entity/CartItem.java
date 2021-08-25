package com.diony.shopping.cart.entity;

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
@TableName("cart_item")
@ApiModel(value="CartItem对象", description="")
public class CartItem implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品的id")
    private Long productId;

    @ApiModelProperty(value = "商品sku的id")
    private Long productSkuId;

    @ApiModelProperty(value = "会员id")
    private Long memberId;

    @ApiModelProperty(value = "购买数量")
    private Integer quantity;

    @ApiModelProperty(value = "添加到购物车的价格")
    private BigDecimal price;

    @ApiModelProperty(value = "销售属性1")
    private String sp1;

    @ApiModelProperty(value = "销售属性2")
    private String sp2;

    @ApiModelProperty(value = "销售属性3")
    private String sp3;

    @ApiModelProperty(value = "商品主图")
    private String productPic;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品品牌")
    private String productBrand;

    @ApiModelProperty(value = "商品的条码")
    private String productSn;

    @ApiModelProperty(value = "商品副标题（卖点）")
    private String productSubTitle;

    @ApiModelProperty(value = "商品sku条码")
    private String productSkuCode;

    @ApiModelProperty(value = "会员昵称")
    private String memberNickname;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "修改时间")
    private Date modifyDate;

    @ApiModelProperty(value = "是否删除")
    private Integer deleteStatus;

    @ApiModelProperty(value = "商品的分类")
    private Long productCategoryId;

    @ApiModelProperty(value = "商品销售属性:[{"key":"颜色","value":"银色"},{"key":"容量","value":"4G"}]")
    private String productAttr;

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
