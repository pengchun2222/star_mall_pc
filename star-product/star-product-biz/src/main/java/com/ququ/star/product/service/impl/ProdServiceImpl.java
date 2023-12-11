package com.ququ.star.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ququ.star.basic.common.exception.Asserts;
import com.ququ.star.common.model.ResultCode;
import com.ququ.star.order.api.model.request.CreateOrderRequest;
import com.ququ.star.product.api.entity.Prod;
import com.ququ.star.product.mapper.ProdMapper;
import com.ququ.star.product.service.ProdService;
import io.seata.core.context.RootContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author 彭淳
 * @since 2023-11-29
 */
@Service
public class ProdServiceImpl extends ServiceImpl<ProdMapper, Prod> implements ProdService {

    @Override
    public Boolean reduceInventory(List<CreateOrderRequest> createOrderRequestList) {
        String xid = RootContext.getXID();
        System.out.println("xid_order:" +xid );
        for (CreateOrderRequest createOrderRequest : createOrderRequestList) {
            Integer flag = baseMapper.reduceInventory(createOrderRequest);
            if(0 == flag) {
                Asserts.fail(ResultCode.INSUFFICIENT_STOCK);
            }
        }
        return true;
    }
}
