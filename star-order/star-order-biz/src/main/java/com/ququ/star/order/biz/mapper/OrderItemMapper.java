package com.ququ.star.order.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ququ.star.order.api.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单项 Mapper 接口
 * </p>
 *
 * @author 彭淳
 * @since 2023-11-29
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}
