package com.cloam.coupon.common;

import com.cloam.coupon.exception.CouponException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;
import java.util.UUID;

/**
 * <h1>用户服务工具类<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 12:33
 */
@Slf4j
@Component
public class CouponUtils {

    private final JavaMailSenderImpl mailSender;

    public CouponUtils(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String from;

    private static final String PWD_BEFORE_KEY = "_Yaien_";
    private static final String PWD_AFTER_KEY = "_Cloam_";
    private static final Integer ENCRYPTION_SUM = 5;

    /**
     * 获取加密后的密码
     *
     * @param salt     盐
     * @param password 待加密密码
     * @return 加密后的密码
     */
    public String passwordEncryption(String salt, String password) {
        StringBuilder builder = new StringBuilder(PWD_BEFORE_KEY + salt + password + PWD_AFTER_KEY);
        for (int i = 0; i < ENCRYPTION_SUM; i++) {
            builder.delete(0, builder.length() / 2);
            builder.append(PWD_AFTER_KEY + password + PWD_BEFORE_KEY + salt);
        }
        String asHex = DigestUtils.md5DigestAsHex(builder.toString().getBytes());
        return asHex;
    }

    /**
     * 获取 code 验证码
     *
     * @return 验证码
     */
    public String getCode() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 发送邮件
     *
     * @param subject 标题
     * @param text    内容
     * @param obj     发送对象
     */
    public void sendEmail(String subject, String text, String... obj) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.setTo(obj);
            helper.setFrom(from);
        } catch (MessagingException e) {
            log.error("email send error,error message is {},send to {}", e.getMessage(), obj);
            throw new CouponException(e.getMessage());
        }
        mailSender.send(mimeMessage);
    }

    /**
     * 获取盐
     *
     * @return 盐
     */
    public String salt() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
