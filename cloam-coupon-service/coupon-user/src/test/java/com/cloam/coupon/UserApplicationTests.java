package com.cloam.coupon;

import com.cloam.coupon.common.CouponUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1><h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 15:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserApplicationTests {

    @Autowired
    private CouponUtils utils;

    @Test
    public void testSendEmail() {
        String subject = "来自 春风视频 的用户注册验证邮箱";
        String content = "(本邮箱是 cloam 程序自动下发，请勿回复！)" +
                "尊敬的用户 123 您好：" +
                "   欢迎注册成为 春风视频 用户，验证码： 656565656；" +
                "                           此致，敬礼。";

        String obj = "2549597630@qq.com";
        utils.sendEmail(subject, content, obj);
    }
}
