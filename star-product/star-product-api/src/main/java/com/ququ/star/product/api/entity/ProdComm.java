package com.ququ.star.product.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品评论
 * </p>
 *
 * @author 彭淳
 * @since 2023-11-29
 */
@Data
public class ProdComm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品ID
     */
    @TableField("prod_id")
    private Long prodId;

    /**
     * 订单项ID
     */
    @TableField("order_item_id")
    private Long orderItemId;

    /**
     * 评论用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 掌柜回复
     */
    @TableField("reply_content")
    private String replyContent;

    /**
     * 记录时间
     */
    @TableField("rec_time")
    private LocalDateTime recTime;

    /**
     * 回复时间
     */
    @TableField("reply_time")
    private LocalDateTime replyTime;

    /**
     * 是否回复 0:未回复  1:已回复
     */
    @TableField("reply_sts")
    private Integer replySts;

    /**
     * IP来源
     */
    @TableField("postip")
    private String postip;

    /**
     * 得分，0-5分
     */
    @TableField("score")
    private Byte score;

    /**
     * 有用的计数
     */
    @TableField("useful_counts")
    private Integer usefulCounts;

    /**
     * 晒图的json字符串
     */
    @TableField("pics")
    private String pics;

    /**
     * 是否匿名(1:是  0:否)
     */
    @TableField("is_anonymous")
    private Integer isAnonymous;

    /**
     * 是否显示，1:为显示，0:待审核， -1：不通过审核，不显示。 如果需要审核评论，则是0,，否则1
     */
    @TableField("status")
    private Integer status;

    /**
     * 评价(0好评 1中评 2差评)
     */
    @TableField("evaluate")
    private Byte evaluate;

    @TableLogic
    private Integer isDelete;
}
