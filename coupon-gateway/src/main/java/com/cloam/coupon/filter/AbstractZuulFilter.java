package com.cloam.coupon.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * <h1>自定义网关抽象过滤器类<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-08-03 15:30
 */
public abstract class AbstractZuulFilter extends ZuulFilter {

    /**
     * 用于在过滤器指尖传递数据，诶个请求的数据保存在各自的 ThreadLocal 中
     */
    RequestContext context;

    private final static String NEXT = "next";

    /**
     * 判断过滤器是否继续往下执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return false;
    }

    /**
     * 过滤器执行的函数
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        context = RequestContext.getCurrentContext();
        return cRun();
    }

    /**
     * 访问失败返回信息给客户端调用的函数
     *
     * @param code
     * @param msg
     * @return
     */
    Object fail(int code, String msg) {
        context.set(NEXT, false);
        context.setSendZuulResponse(false);
        context.getResponse().setContentType("text/html;charset=UTF-8");
        context.setResponseStatusCode(code);
        context.setResponseBody(String.format("{\"result\":\"%S|\"}", msg));

        return null;
    }

    /**
     * 访问成功调用的函数
     *
     * @return
     */
    Object success() {
        context.set(NEXT, true);
        return null;
    }

    protected abstract Object cRun();
}
