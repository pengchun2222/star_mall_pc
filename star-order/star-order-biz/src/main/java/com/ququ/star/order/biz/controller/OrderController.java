package com.ququ.star.order.biz.controller;

import com.ququ.star.common.model.CommonResult;
import com.ququ.star.order.api.model.request.CreateOrderRequest;
import com.ququ.star.order.biz.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 彭淳
 * @Date: 2023/11/22 15:00
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 下单
     * @return
     */
    @PostMapping("/createOrder")
    public CommonResult createOrder(@RequestBody List<CreateOrderRequest> createOrderRequestList) {
        Long orderId = orderService.createOrder(createOrderRequestList);
        return CommonResult.success(orderId);
    }

    /**
     * 下单
     * @return
     */
    @GetMapping("/pay/{orderId}")
    public CommonResult pay(@PathVariable("orderId") Long orderId) {
        orderService.pay(orderId);
        return CommonResult.success();
    }
}
