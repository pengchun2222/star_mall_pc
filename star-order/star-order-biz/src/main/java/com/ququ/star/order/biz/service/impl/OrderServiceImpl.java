package com.ququ.star.order.biz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ququ.star.basic.common.exception.Asserts;
import com.ququ.star.basic.common.utils.SnowflakeIdGenerator;
import com.ququ.star.common.model.LoginVal;
import com.ququ.star.common.utils.OauthUtils;
import com.ququ.star.order.api.entity.Order;
import com.ququ.star.order.api.entity.OrderItem;
import com.ququ.star.order.api.model.request.CreateOrderRequest;
import com.ququ.star.order.biz.mapper.OrderMapper;
import com.ququ.star.order.biz.service.OrderItemService;
import com.ququ.star.order.biz.service.OrderService;
import com.ququ.star.product.api.entity.Prod;
import com.ququ.star.product.api.feign.RemoteProdService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author 彭淳
 * @since 2023-11-29
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final RemoteProdService remoteProdService;
    private final OrderItemService orderItemService;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public Long createOrder(List<CreateOrderRequest> createOrderRequestList) {
        String xid = RootContext.getXID();
        System.out.println("xid_order:" +xid );
        Map<Long, Integer> createOrderRequestMap = createOrderRequestList.stream().collect(Collectors.toMap(CreateOrderRequest::getProdId, CreateOrderRequest::getCount));

        // 查询商品信息
        List<Prod> prodList = remoteProdService.queryProByIds(createOrderRequestList.stream().map(CreateOrderRequest::getProdId).collect(Collectors.toList()));

        prodList.forEach(prod -> {
            Integer count = createOrderRequestMap.get(prod.getId());
            //if (prod.getTotalStocks() - count < 0 ) {
            //    Asserts.fail("商品库存不足");
            //}
            // 减库存
            Boolean data = remoteProdService.reduceInventory(createOrderRequestList).getData();
            if(!data) {
                Asserts.fail("商品库存不足");
            }
        });

        // 创建订单
        Order order = new Order();
        order.setProdName(prodList.stream().map(Prod::getName).collect(Collectors.joining()));
        order.setUserId(OauthUtils.getCurrentUser().getUserId());
        order.setOrderNumber(SnowflakeIdGenerator.nextId());
        order.setTotal(prodList.stream().map(Prod::getOriPrice).reduce((bigDecimal, bigDecimal2) -> bigDecimal.add(bigDecimal2)).get());
        order.setTotal(prodList.stream().map(Prod::getPrice).reduce((bigDecimal, bigDecimal2) -> bigDecimal.add(bigDecimal2)).get());
        order.setRemarks("顺丰");
        order.setStatus(1);
        order.setProductNums(createOrderRequestList.stream().map(CreateOrderRequest::getCount).reduce((integer, integer2) -> integer + integer2).get());

        save(order);

        List<OrderItem> orderItemList = new ArrayList<>();
        for (Prod prod : prodList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderNumber(order.getOrderNumber());
            orderItem.setProdId(prod.getId());
            orderItem.setProdName(prod.getName());
            orderItem.setUserId(OauthUtils.getCurrentUser().getUserId());
            orderItem.setPrice(prod.getPrice());
            orderItem.setProductTotalAmount(orderItem.getPrice().multiply(BigDecimal.valueOf(createOrderRequestMap.get(prod.getId()))));
            orderItem.setRecTime(LocalDateTime.now());
            orderItemList.add(orderItem);
        }
        orderItemService.saveBatch(orderItemList);
        return order.getId();
    }

    @Override
    public void pay(Long orderId) {
        Order order = getById(orderId);
        order.setPayType(1);
        order.setPayTime(LocalDateTime.now());
        order.setStatus(2);
        updateById(order);

        List<OrderItem> list = orderItemService.list(Wrappers.lambdaQuery(OrderItem.class).eq(OrderItem::getOrderNumber, order.getOrderNumber()));
        for (OrderItem orderItem : list) {
            orderItem.getProdId();
        }
    }


}
