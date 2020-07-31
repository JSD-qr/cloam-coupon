package com.cloam.coupon.service.impl;

import com.cloam.coupon.common.CouponUtils;
import com.cloam.coupon.entity.CouponUser;
import com.cloam.coupon.mapper.CouponUserMapper;
import com.cloam.coupon.service.ILoginSystemService;
import com.cloam.coupon.Constant;
import com.cloam.coupon.exception.CouponException;
import com.cloam.coupon.utils.RedisCacheUtils;
import com.cloam.coupon.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * <h1>系统登录业务实现类<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 12:28
 */
@Slf4j
@Service
public class LoginSystemServiceImpl implements ILoginSystemService {

    private final CouponUserMapper userMapper;
    private final CouponUtils utils;
    private final RedisCacheUtils redisCache;

    public LoginSystemServiceImpl(CouponUserMapper userMapper, CouponUtils utils, RedisCacheUtils redisCache) {
        this.userMapper = userMapper;
        this.utils = utils;
        this.redisCache = redisCache;
    }

    @Override
    public CommonResponse login(String userName, String password) {
        CouponUser user = userMapper.findByUserName(userName);

        if (ObjectUtils.isEmpty(user) || !user.isStatus()) {
            log.error("Query failed, this user:{} is non-existent.", userName);
            throw new CouponException("登录失败，不存在此用户");
        }

        String requestPwd = utils.passwordEncryption(user.getSalt(), password);
        if (!user.getPassword().equals(requestPwd)) {
            log.error("Verification failed, user:{} password error.", userName);
            throw new CouponException("登录失败，密码错误");
        }

        return new CommonResponse(HttpStatus.OK.value());
    }

    @Override
    public void getCode(String userName) {
        if (StringUtils.isEmpty(userName)) {
            log.error("get code fail, email is null!");
            throw new CouponException("get code fail, email is null!");
        }
        String code = utils.getCode();
        boolean isTrue = redisCache.set(Constant.RedisPrefix.USER_INSERT_CODE + code, Constant.DEFAULT_CODE, 180);
        if (isTrue) {
            String subject = "来自春风视频的注册邮件";
            String content = "(本邮箱是 cloam 程序自动下发，请勿回复！)\r\n" +
                    "尊敬的用户 123 您好：\r\n" +
                    "\t欢迎注册成为 春风视频 用户，本次验证码有效期 3 分钟，\r\n" +
                    "\t请在有效期内输入验证" +
                    "\t\t验证码：" + code +
                    "\t\t\t此致，敬礼.";

            utils.sendEmail(subject, content, userName);
            log.info("send code to {} success. code: {}", userName, code);
        }
    }

}
