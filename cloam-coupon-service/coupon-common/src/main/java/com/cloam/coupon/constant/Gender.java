package com.cloam.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * <h1>性别<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 09:37
 */
@Getter
@AllArgsConstructor
public enum Gender {

    GIRL("女", "0"),
    BOY("男", "1"),
    UNKNOWN("未知", "-1");

    private String name;
    private String code;

    public static Gender of(String code) {
        Objects.requireNonNull(code);

        return Stream.of(values())                                                          // 获取所有枚举信息
                .filter(bean -> bean.code.equals(code))                                     // 根据 code 进行过滤
                .findAny()                                                                  // 选择筛选后的枚举
                .orElseThrow(() -> new IllegalArgumentException(code + "not exists!"));     // 返回对应枚举或抛出异常
    }
}
