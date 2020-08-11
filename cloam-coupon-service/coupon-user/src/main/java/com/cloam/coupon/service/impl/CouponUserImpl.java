package com.cloam.coupon.service.impl;

import com.cloam.coupon.Constant;
import com.cloam.coupon.common.CouponUtils;
import com.cloam.coupon.common.UserIdWorker;
import com.cloam.coupon.entity.CouponUser;
import com.cloam.coupon.exception.CouponException;
import com.cloam.coupon.mapper.CouponUserMapper;
import com.cloam.coupon.service.ICouponUserService;
import com.cloam.coupon.utils.RedisCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * <h1>用户相关业务层实现类<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 11:48
 */
@Slf4j
@Service
public class CouponUserImpl implements ICouponUserService {

    private final CouponUtils utils;
    private final CouponUserMapper mapper;
    private final RedisCacheUtils redisCache;
    private final CouponUserMapper userMapper;

    public CouponUserImpl(CouponUserMapper mapper, CouponUtils utils, RedisCacheUtils redisCache, CouponUserMapper userMapper) {
        this.mapper = mapper;
        this.utils = utils;
        this.redisCache = redisCache;
        this.userMapper = userMapper;
    }

    @Override
    public void getCode(String userName) {
        if (StringUtils.isEmpty(userName)) {
            log.error("get code fail, email is null!");
            throw new CouponException("get code fail, email is null!");
        }
        String code = utils.getCode();
        boolean isTrue = redisCache.set(Constant.RedisPrefix.USER_INSERT_CODE + userName, code, 180);
        if (isTrue) {
            String subject = "来自亚恩服务";

            String content = "<!DOCTYPE html><html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title></title><meta charset='utf-8' />\n" +
                    "</head><body><div class='qmbox qm_con_body_content qqmail_webmail_only' id='mailContentContainer' style=''><style type='text/css'>.qmbox body \n" +
                    "{ margin: 0; padding: 0;background: #fff;font-family: 'Verdana, Arial, Helvetica, sans-serif';font-size: 14px;line-height: 24px;}.qmbox div,\n" +
                    ".qmbox p,.qmbox span,.qmbox img {margin: 0;padding: 0;}.qmbox img { border: none;}.qmbox .contaner {margin: 0 auto;}.qmbox .title {margin: 0 auto;\n" +
                    "background: url() #CCC repeat-x;height: 30px;text-align: center;font-weight: bold;padding-top: 12px;font-size: 16px;}.qmbox .content {margin: 4px;}\n" +
                    ".qmbox .biaoti {padding: 6px; color: #000;}.qmbox .xtop,.qmbox .xbottom {display: block;font-size: 1px;}.qmbox .xb1,.qmbox .xb2,.qmbox .xb3,.qmbox \n" +
                    ".xb4 {display: block;overflow: hidden;}.qmbox .xb1,.qmbox .xb2,.qmbox .xb3 {height: 1px;}.qmbox .xb2,.qmbox .xb3,.qmbox .xb4 {border-left: 1px solid #BCBCBC;\n" +
                    "border-right: 1px solid #BCBCBC;}.qmbox .xb1 {margin: 0 5px;background: #BCBCBC;}.qmbox .xb2 {margin: 0 3px;border-width: 0 2px;}.qmbox .xb3 {margin: 0 2px;}\n" +
                    ".qmbox .xb4 {height: 2px;margin: 0 1px;}.qmbox .xboxcontent {display: block;border: 0 solid #BCBCBC;border-width: 0 1px;}.qmbox .line {margin-top: 6px;\n" +
                    "border-top: 1px dashed #B9B9B9;padding: 4px;}.qmbox .neirong {padding: 6px;color: #666666;}.qmbox .foot {padding: 6px;color: #777;}\n" +
                    ".qmbox .font_darkblue {color: #006699;font-weight: bold;}.qmbox .font_lightblue {color: #008BD1;font-weight: bold;}.qmbox .font_gray {color: #888;font-size: 12px;}\n" +
                    "</style><div class='contaner'><div class='title'> 亚恩服务技术团队 </div><div class='content'><p class='biaoti'><b>亲爱的用户，你好！</b></p>\n" +
                    "<b class='xtop'><b class='xb1'></b><b class='xb2'></b><b class='xb3'></b><b class='xb4'></b></b><div class='xboxcontent'><div class='neirong'><p>\n" +
                    "<b>请核对你的用户名：</b><span id='userName' class='font_darkblue'>" + userName + "</span></p><p><b>新用户注册验证码：</b><span class='font_lightblue'>\n" +
                    "<span id='yzm' data='" + code + "' onclick='return false;'t='7' style='border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;'>\n" + code +
                    "</span></span><br><span class='font_gray'>(请输入该验证码完成新用户注册，验证码3分钟内有效！)</span></p><div class='line'>如果你未申请新用户注册服务，请忽略该邮件。\n" +
                    "</div> </div></div><b class='xbottom'><b class='xb4'></b><b class='xb3'></b><b class='xb2'></b><b class='xb1'></b></b><p class='foot'>如果仍有问题，请拨打我们的会员服务专线: \n" +
                    "<span data='800-820-****' onclick='return false;' t='7'style='border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;'>021-51878***</span></p></div>\n" +
                    "</div><style type='text/css'>.qmbox style,.qmbox script,.qmbox head,.qmbox link,.qmbox meta {display: none !important;}</style></div></body></html>";

            utils.sendEmail(subject, content, userName);
            log.info("send code to {} success. code: {}", userName, code);
        }
    }

    @Override
    public void regUser(String userName, String password, String code) {
        CouponUser result = mapper.findByUserName(userName);
        if (!ObjectUtils.isEmpty(result)) {
            log.error("is then user name {} already registered", userName);
            throw new CouponException("该用户名已经被使用");
        }

        String resCOde = redisCache.get(Constant.RedisPrefix.USER_INSERT_CODE + userName);
        if (!code.equals(resCOde)) {
            log.error("check code error, paraCode: {}, cacheCOde: {}", code, resCOde);
            throw new CouponException("校验码输入错误");
        }

        CouponUser user = new CouponUser();
        user.setId(UserIdWorker.getId());
        user.setNickName(userName);
        user.setUserName(userName);
        String salt = utils.salt();
        user.setSalt(salt);
        user.setGender("-1");
        user.setPortrait("-1");
        user.setStatus("0");
        user.setPassword(utils.passwordEncryption(salt, password));
        user.setCreate_time(new Timestamp(new Date().getTime()));

        mapper.save(user);
    }

    @Override
    public void login(String userName, String password) {
        CouponUser user = userMapper.findByUserName(userName);

        if (ObjectUtils.isEmpty(user) || "1".equals(user.getStatus())) {
            log.error("Query failed, this user:{} is non-existent.", userName);
            throw new CouponException("登录失败，不存在此用户");
        }

        String requestPwd = utils.passwordEncryption(user.getSalt(), password);
        if (!user.getPassword().equals(requestPwd)) {
            log.error("Verification failed, user:{} password error.", userName);
            throw new CouponException("登录失败，密码错误");
        }


    }

    @Override
    public boolean isUser(String userName) {
        return !ObjectUtils.isEmpty(userMapper.findByUserNameAndStatus(userName,"0"));
    }

    @Override
    public void resetPwd(String userName,String password,String code) {

        CouponUser user = userMapper.findByUserNameAndStatus(userName,"0");
        if (ObjectUtils.isEmpty(user)){
           log.error("Query failed, this user:{} is non-existent.", userName);
            throw new CouponException("用户不存在");
        }

        String resCOde = redisCache.get(Constant.RedisPrefix.USER_INSERT_CODE + userName);
        if (!code.equals(resCOde)) {
            log.error("check code error, paraCode: {}, cacheCOde: {}", code, resCOde);
            throw new CouponException("校验码输入错误");
        }

        user.setPassword(utils.passwordEncryption(user.getSalt(),password));
        userMapper.save(user);
        log.info("resetPwd success,after modification password: {}",password);
    }


}
