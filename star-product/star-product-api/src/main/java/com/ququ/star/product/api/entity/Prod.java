package com.ququ.star.product.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品
 * </p>
 *
 * @author 彭淳
 * @since 2023-11-29
 */
@Data
public class Prod implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    @TableField("name")
    private String name;

    /**
     * 原价
     */
    @TableField("ori_price")
    private BigDecimal oriPrice;

    /**
     * 现价
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 简要描述,卖点等
     */
    @TableField("brief")
    private String brief;

    /**
     * 详细描述
     */
    @TableField("content")
    private String content;

    /**
     * 商品主图
     */
    @TableField("pic")
    private String pic;

    /**
     * 商品图片，以,分割
     */
    @TableField("imgs")
    private String imgs;

    /**
     * 默认是0，1表示下架
     */
    @TableField("status")
    private Integer status;

    /**
     * 销量
     */
    @TableField("sold_num")
    private Integer soldNum;

    /**
     * 总库存
     */
    @TableField("total_stocks")
    private Integer totalStocks;

    /**
     * 录入时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 上架时间
     */
    @TableField("putaway_time")
    private LocalDateTime putawayTime;

    /**
     * 默认是0，1 删除
     */
    @TableLogic
    private Integer isDelete;

}
