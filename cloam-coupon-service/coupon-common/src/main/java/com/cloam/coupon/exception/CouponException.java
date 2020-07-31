package com.cloam.coupon.exception;

/**
 * <h1>通用异常定义<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 09:03
 */
public class CouponException extends RuntimeException {

    public CouponException(String message) {
        super(message);
    }

}
