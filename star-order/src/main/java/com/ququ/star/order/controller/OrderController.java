package com.ququ.star.order.controller;

import com.ququ.star.common.model.LoginVal;
import com.ququ.star.common.utils.OauthUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 彭淳
 * @Date: 2023/11/22 15:00
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @PostMapping("/info")
    public LoginVal login() {
        return OauthUtils.getCurrentUser();
    }
}
