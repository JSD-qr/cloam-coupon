package com.cloam.coupon;

/**
 * <h1>通用常量定义<h1>
 *
 * @program: yaien-coupon
 * @author: Yaien
 * @create: 2020-03-20 12:03
 */
public class Constant {

    // kafka 消息的 Topic
    public static final String TOPIC = "cloam_user_coupon_op";
    public static final String DEFAULT_CODE = "-1";


    /**
     * <h2>Redis Key 前缀定义</h2>
     */
    public static class RedisPrefix {
        //用户校验码 key 前缀
        public static final String USER_INSERT_CODE = "cloam_insert_coupon_user_code_";

        //用户当前所有可用的优惠券 key 前缀
        public static final String USER_COUPON_USABLE = "yaien_user_coupon_usable_";

        //用户当前所有已使用的优惠券 key 前缀
        public static final String USER_COUPON_USED = "yaien_user_coupon_used_";

        //用户当前所有已过期的优惠券 keu 前缀
        public static final String USER_COUPON_EXPIRED = "yaien_user_coupon_expired_";
    }
}
