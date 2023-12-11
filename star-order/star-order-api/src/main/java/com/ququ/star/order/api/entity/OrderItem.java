package com.ququ.star.order.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单项
 * </p>
 *
 * @author 彭淳
 * @since 2023-11-29
 */
@Data
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单项ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单order_number
     */
    private Long orderNumber;

    /**
     * 产品ID
     */
    private Long prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品主图片路径
     */
    private String pic;

    /**
     * 产品价格
     */
    private BigDecimal price;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 商品总金额
     */
    private BigDecimal productTotalAmount;

    /**
     * 购物时间
     */
    private LocalDateTime recTime;

    /**
     * 评论状态： 0 未评价  1 已评价
     */
    private Integer commSts;

    /**
     * 推广员使用的推销卡号
     */
    private String distributionCardNo;

    /**
     * 加入购物车时间
     */
    private LocalDateTime basketDate;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableLogic
    private Integer isDelete;
}
