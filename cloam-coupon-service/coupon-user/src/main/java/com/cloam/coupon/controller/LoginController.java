package com.cloam.coupon.controller;

import com.cloam.coupon.annotation.IgnoreResponseAdvice;
import com.cloam.coupon.service.ILoginSystemService;
import com.cloam.coupon.vo.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>用户登录控制器<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 13:32
 */
@RestController("/")
public class LoginController {

    private final ILoginSystemService loginSystemService;

    public LoginController(ILoginSystemService loginSystemService) {
        this.loginSystemService = loginSystemService;
    }

    @PostMapping("/login")
    public CommonResponse login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        return loginSystemService.login(userName, password);
    }

    @IgnoreResponseAdvice
    @GetMapping("/getCode")
    public void getCode(@RequestParam("userName") String userName) {
        loginSystemService.getCode(userName);
    }
}
