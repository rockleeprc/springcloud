package org.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

@Component
public class MyFilter extends ZuulFilter {
    /**
     * 过滤器类型
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序，按数值从小到大调用过滤器
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 当前过滤器是否生效
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器中具体业务逻辑
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("过滤器执行");
        RequestContext context = RequestContext.getCurrentContext();
//        context.setRequest(new MyRequest(context.getRequest()));
//        HttpServletRequest request = context.getRequest();
//        String message = request.getParameter("message");
        return null;
    }

//    static class MyRequest extends HttpServletRequestWrapper {
//        public MyRequest(HttpServletRequest request) {
//            super(request);
//        }
//
//        @Override
//        public String getParameter(String name) {
//            if (name != null && "message".equals(name)) {
//                return getParameter("message") + "{zuul filter}";
//            }
//            return super.getParameter(name);
//        }
//    }


}
