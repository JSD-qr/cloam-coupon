package com.cloam.coupon.mapper;

import com.cloam.coupon.entity.CouponUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1>CouponUser Mapper 接口定义<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 11:39
 */
public interface CouponUserMapper extends JpaRepository<CouponUser, Integer> {

    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    CouponUser findByUserName(String userName);

    /**
     * 根据用户名与状态查询用户信息
     * @param userName
     * @param status
     * @return
     */
    CouponUser findByUserNameAndStatus(String userName, String status);

}
