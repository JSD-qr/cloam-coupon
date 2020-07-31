package com.cloam.coupon.advice;


import com.cloam.coupon.annotation.IgnoreResponseAdvice;
import com.cloam.coupon.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <h1>返回统一响应前处理<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-07-31 09:12
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice {

    /**
     * <h2>判断是否需要对相应进行处理</h2>
     *
     * @param methodParameter 当前 controller 方法的定义
     * @param aClass          消息转换器
     * @return 是否需要处理
     */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class aClass) {

        // 当前类是否标识 @IgnoreResponseAdvice
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) return false;

        // 当前类是否标识 @IgnoreResponseAdvice
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) return false;

        // 进行处理，执行 beforeBodyWrite 函数
        return true;
    }

    /**
     * <h2>响应响应前的处理</h2>
     *
     * @param o                  controller 返回的对象
     * @param methodParameter    controller 方法的定义
     * @param mediaType          返回对象类型
     * @param aClass             消息转换器
     * @param serverHttpRequest  信息请求对象
     * @param serverHttpResponse 请求响应对象
     * @return 响应对象
     */
    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        // 最终的返回对象
        CommonResponse<Object> response = new CommonResponse<>(0, "");

        if (null == o) {
            return response;
        } else if (o instanceof CommonResponse) {
            response = (CommonResponse<Object>) o;
        } else {
            response.setData(o);
        }

        return response;
    }
}
