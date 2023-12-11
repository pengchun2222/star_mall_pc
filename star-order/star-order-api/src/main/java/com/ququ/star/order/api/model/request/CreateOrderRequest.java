package com.ququ.star.order.api.model.request;

import lombok.Data;

/**
 * @Author: 彭淳
 * @Date: 2023/11/29 18:03
 */
@Data
public class CreateOrderRequest {
    private Long prodId;
    private Integer count;
}
