package com.cloam.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <h1>统一相应对象<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 08:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public CommonResponse(Integer code) {
        this.code = code;
    }

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
