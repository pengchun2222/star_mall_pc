package com.ququ.star.order.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ququ.star.order.api.entity.Order;
import com.ququ.star.order.api.model.request.CreateOrderRequest;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author 彭淳
 * @since 2023-11-29
 */
public interface OrderService extends IService<Order> {
    /**
     * 创建订单
     * @param createOrderRequestList
     * @return
     */
    Long createOrder(List<CreateOrderRequest> createOrderRequestList);

    /**
     * 支付
     * @param orderId
     */
    void pay(Long orderId);
}
