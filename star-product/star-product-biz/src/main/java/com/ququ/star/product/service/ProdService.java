package com.ququ.star.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ququ.star.order.api.model.request.CreateOrderRequest;
import com.ququ.star.product.api.entity.Prod;

import java.util.List;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author 彭淳
 * @since 2023-11-29
 */
public interface ProdService extends IService<Prod> {

    Boolean reduceInventory(List<CreateOrderRequest> createOrderRequestList);
}
