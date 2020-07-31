package com.cloam.coupon.service.impl;

import com.cloam.coupon.common.CouponUtils;
import com.cloam.coupon.service.ICouponUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1><h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 11:48
 */
@Service
public class CouponUserImpl implements ICouponUserService {

    @Autowired
    private CouponUtils utils;

    @Override
    public void addUser(String userName, String password, String code) {

    }


}
