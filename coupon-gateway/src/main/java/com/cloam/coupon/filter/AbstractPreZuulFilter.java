package com.cloam.coupon.filter;


import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * <h1>Pre时执行的抽象过滤器类<h1>
 *
 * @program: cloam-coupon
 * @author: Yaien
 * @create: 2020-08-03 15:47
 */
public abstract class AbstractPreZuulFilter extends AbstractZuulFilter{
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
}
