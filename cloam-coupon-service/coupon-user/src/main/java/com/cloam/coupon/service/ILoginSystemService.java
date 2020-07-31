package com.cloam.coupon.service;

import com.cloam.coupon.vo.CommonResponse;

/**
 * <h1>用户登录业务层接口<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 11:50
 */
public interface ILoginSystemService {

    /**
     * <h2>登录</h2>
     */
    CommonResponse login(String userName, String password);

    /**
     * <h2>获取注册验证码</h2>
     */
    void getCode(String userName);
}
