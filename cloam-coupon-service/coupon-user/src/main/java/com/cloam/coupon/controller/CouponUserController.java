package com.cloam.coupon.controller;

import com.cloam.coupon.service.ICouponUserService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>用户服务控制器<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 13:32
 */
@RestController("/user")
public class CouponUserController {

    private final ICouponUserService userService;

    public CouponUserController(ICouponUserService userService) {
        this.userService = userService;
    }

}
