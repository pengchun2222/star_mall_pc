package com.ququ.star.product.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ququ.star.order.api.model.request.CreateOrderRequest;
import com.ququ.star.product.api.entity.Prod;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author 彭淳
 * @since 2023-11-29
 */
@Mapper
public interface ProdMapper extends BaseMapper<Prod> {

    Integer reduceInventory(@Param("param") CreateOrderRequest createOrderRequest);
}
