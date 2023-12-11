package com.ququ.star.product.api.feign;

//import com.ququ.star.common.constent.ServiceNameConstants;
import com.ququ.star.common.constent.ServiceNameConstants;
import com.ququ.star.common.model.CommonResult;
import com.ququ.star.order.api.model.request.CreateOrderRequest;
import com.ququ.star.product.api.entity.Prod;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: 彭淳
 * @Date: 2023/11/30 17:48
 */
@FeignClient(contextId = "RemoteProdService",value = ServiceNameConstants.PRODUCT_SERVICE)
public interface RemoteProdService {
    /**
     * 通过id集合查询产品信息
     * @param prodIdList
     * @return
     */
    @PostMapping("/prod/queryProByIds")
    List<Prod> queryProByIds(@RequestBody List<Long> prodIdList);

    /**
     * 下单减库存
     * @param createOrderRequestList
     * @return
     */
    @PostMapping("/prod/reduceInventory")
    CommonResult<Boolean> reduceInventory(@RequestBody List<CreateOrderRequest> createOrderRequestList);
}
