package org.spring.cloud.architect.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 编写一个zuul过滤器，用来控制权限。每次发送请求的时候都需要一个token，如果没有token就没有权限，在被路由之前过滤器就会自动拦截掉返回401错误。
 */
@Slf4j
@Component
public class CloudZuulFilter extends ZuulFilter {

    /**
     * 返回一个字符串代表过滤器的类型
     * pre表示被路由之前
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;//定义filter的类型，有pre、route、post、error四种
    }

    /**
     * 过滤顺序
     */

    @Override
    public int filterOrder() {
        return 0; //定义filter的顺序，数字越小表示顺序越高，越先执行
    }

    @Override
    public boolean shouldFilter() {
        return true; //表示是否需要执行该filter，true表示执行，false表示不执行
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String accessToken = String.valueOf(request.getParameter("token"));
        log.info("--------------------------token=" + accessToken);
        if (!accessToken.equals("abcde")) {
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(401);
            return "";
        } else {
            if (StringUtils.isNotBlank(accessToken)) {
                ctx.setSendZuulResponse(true);//对请求进行路由
                ctx.setResponseStatusCode(200);
//                ctx.set("isSuccess", true);
                return null;
            }

        }
        return null;
    }

}