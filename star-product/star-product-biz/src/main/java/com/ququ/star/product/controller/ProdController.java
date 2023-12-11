package com.ququ.star.product.controller;

import com.ququ.star.common.model.CommonResult;
import com.ququ.star.common.model.LoginVal;
import com.ququ.star.common.utils.OauthUtils;
import com.ququ.star.order.api.model.request.CreateOrderRequest;
import com.ququ.star.product.api.entity.Prod;
import com.ququ.star.product.service.ProdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品 前端控制器
 * </p>
 *
 * @author 彭淳
 * @since 2023-11-29
 */
@RestController
@RequestMapping("/prod")
@RequiredArgsConstructor
@Slf4j
public class ProdController {

    private final ProdService prodService;

    @PostMapping("/queryProByIds")
    public List<Prod> queryProByIds(@RequestBody List<Long> prodIdList){
        return prodService.listByIds(prodIdList);
    }

    /**
     * 下单减库存
     * @param createOrderRequestList
     * @return
     */
    @PostMapping("/reduceInventory")
    CommonResult<Boolean> reduceInventory(@RequestBody List<CreateOrderRequest> createOrderRequestList){
        return CommonResult.success(prodService.reduceInventory(createOrderRequestList));
    }
}
