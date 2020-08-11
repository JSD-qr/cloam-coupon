package com.cloam.coupon.service;

import com.cloam.coupon.vo.CommonResponse;

/**
 * <h1>用户相关业务接口<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 11:45
 */
public interface ICouponUserService {

    /**
     * <h2>获取注册验证码</h2>
     */
    void getCode(String userName);

    /**
     * <h2> 新增用户 </h2>
     *
     * @param userName 用户名
     * @param password 密码
     */
    void regUser(String userName, String password, String code);

    /**
     * <h2>登录</h2>
     */
    void login(String userName, String password);

    /**
     * <h2> 用户是否存在 </h2>
     * @param userName 用户明 true:存在
     */
    boolean isUser(String userName);

    /**
     * <h2> 重置用户密码 </h2>
     *
     * @param userName 用户名
     * @param password  密码
     * @param code 校验码
     *
     */
    void resetPwd(String userName,String password,String code);

}
