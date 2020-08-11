package com.cloam.coupon.controller;

import com.cloam.coupon.annotation.IgnoreResponseAdvice;
import com.cloam.coupon.service.ICouponUserService;
import com.cloam.coupon.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>用户服务控制器<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 13:32
 */
@RestController("/")
public class CouponUserController {

    private final ICouponUserService userService;

    public CouponUserController(ICouponUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/regUser")
    public CommonResponse regUser(String userName, String password, String code) {
        userService.regUser(userName, password, code);
        return new CommonResponse<>(HttpStatus.OK.value());
    }

    @IgnoreResponseAdvice
    @GetMapping("/getCode")
    public void getCode(@RequestParam("userName") String userName) {
        userService.getCode(userName);
    }

    @PostMapping("/login")
    public CommonResponse login(String userName,String password) {
         userService.login(userName, password);
        return new CommonResponse(HttpStatus.OK.value());
    }

    @IgnoreResponseAdvice
    @GetMapping("/isUser")
    public boolean isUser(String userName) {
        return userService.isUser(userName);
    }

    @PostMapping("/resetPwd")
    public CommonResponse resetPwd(String userName,String password,String code) {
        userService.resetPwd(userName, password,code);
        return new CommonResponse<>(HttpStatus.OK.value());
    }
}
