package com.ququ.star.order.api.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author 彭淳
 * @since 2023-11-29
 */
@Data
@TableName("order_info")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long id;

    /**
     * 产品名称,多个产品将会以逗号隔开
     */
    private String prodName;

    /**
     * 订购用户ID
     */
    private String userId;

    /**
     * 订购流水号
     */
    private Long orderNumber;

    /**
     * 总值
     */
    private BigDecimal total;

    /**
     * 实际总值
     */
    private BigDecimal actualTotal;

    /**
     * 支付方式 0 手动代付 1 微信支付 2 支付宝
     */
    private Integer payType;

    /**
     * 订单备注
     */
    private String remarks;

    /**
     * 订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败
     */
    private Integer status;

    /**
     * 配送类型
     */
    private String dvyType;

    /**
     * 配送方式ID
     */
    private Long dvyId;

    /**
     * 订单运费
     */
    private BigDecimal freightAmount;

    /**
     * 订单商品总数
     */
    private Integer productNums;

    /**
     * 订购时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 订单更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 付款时间
     */
    private LocalDateTime payTime;

    /**
     * 完成时间
     */
    private LocalDateTime finallyTime;

    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;

    /**
     * 用户订单删除状态，0：没有删除， 1：删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 0:默认,1:在处理,2:处理完成
     */
    private Integer refundSts;

    /**
     * 优惠总额
     */
    private BigDecimal reduceAmount;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 订单关闭原因 1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易
     */
    private Integer closeType;
}
