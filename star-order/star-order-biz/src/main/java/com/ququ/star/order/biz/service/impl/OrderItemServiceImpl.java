package com.ququ.star.order.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ququ.star.order.api.entity.OrderItem;
import com.ququ.star.order.biz.mapper.OrderItemMapper;
import com.ququ.star.order.biz.service.OrderItemService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单项 服务实现类
 * </p>
 *
 * @author 彭淳
 * @since 2023-11-29
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
