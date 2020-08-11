package com.cloam.coupon.advice;


import com.cloam.coupon.exception.CouponException;
import com.cloam.coupon.vo.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>全局异常消息处理<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 09:28
 */
@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionAdvice {

    /**
     * <h2>对 CouponException 进行统一处理</h2>
     *
     * @param request request 请求
     * @param ex      异常对象
     * @return 处理过的异常对象
     */
    @ExceptionHandler(value = CouponException.class)
    @ResponseBody
    public CommonResponse<String> handlerCouponException(HttpServletRequest request, CouponException ex) {
        CommonResponse<String> response = new CommonResponse<>(-1, "business error");
        response.setData(ex.getMessage());
        return response;
    }
}
