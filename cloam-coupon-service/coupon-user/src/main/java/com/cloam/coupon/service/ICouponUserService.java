package com.cloam.coupon.service;

/**
 * <h1>用户相关业务接口<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 11:45
 */
public interface ICouponUserService {

    /**
     * <h2> 新增用户 </h2>
     *
     * @param userName 用户名
     * @param password 密码
     */
    void addUser(String userName, String password, String code);


}
