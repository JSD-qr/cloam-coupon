package com.cloam.coupon.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <h1>Redis 缓存工具类<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 16:07
 */
@Slf4j
@Component
public class RedisCacheUtils {

    private final static long DEFAULT_TIME = 600L;
    private final StringRedisTemplate redisTemplate;

    public RedisCacheUtils(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 查看 key 是否存在
     *
     * @param key 键
     * @return
     */
    public boolean isExist(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取数据
     *
     * @param key 键
     * @return 值
     */
    public String get(final String key) {
        if (isExist(key)) {
            return redisTemplate.opsForValue().get(key);
        }
        return "";
    }

    /**
     * 新增缓存数据
     *
     * @param key   键
     * @param value 值
     * @return 是否存入成功
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            log.error("add redis date fail! key:{}, value:{}, error message: {}", key, value, e.getMessage());
        }
        return result;
    }

    /**
     * 新增缓存数据
     *
     * @param key      键
     * @param value    值
     * @param isExpiry 是否开启过期时间，默认 600s
     * @return 是否存入成功
     */
    public boolean set(final String key, String value, boolean isExpiry) {
        boolean result = false;
        if (isExpiry) {
            result = set(key, value, DEFAULT_TIME);
        } else {
            result = set(key, value);
        }
        return result;
    }

    /**
     * 新增缓存数据
     *
     * @param key        键
     * @param value      值
     * @param expiryTime 有效期 (单位： 秒)
     * @return 是否存入成功
     */
    public boolean set(final String key, String value, long expiryTime) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value, expiryTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error("add redis date fail! key:{}, value:{}, error message: {}", key, value, e.getMessage());
        }
        return result;
    }

    /**
     * 更新缓存
     */
    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            log.error("update redis date fail! key:{}, value:{}, error message: {}", key, value, e.getMessage());
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            log.error("delete redis date fail! key:{}, error message: {}", key, e.getMessage());
        }
        return result;
    }
}
