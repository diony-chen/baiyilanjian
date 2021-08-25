package com.diony.shopping.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "update_by", jdbcType = JdbcType.BIGINT, fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_by", jdbcType = JdbcType.BIGINT, fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    @Version
    @TableField(value = "version", jdbcType = JdbcType.INTEGER, fill = FieldFill.UPDATE)
    private Integer version;

    @ApiModelProperty(value = "删除")
    private Integer deleted;
}
