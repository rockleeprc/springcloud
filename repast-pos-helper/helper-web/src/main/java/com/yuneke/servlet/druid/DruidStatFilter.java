package com.yuneke.servlet.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * TODO:
 *      引入druid-spring-boot-starter后
 *      WebStatFilter、StatViewServlet通过@ServletComponentScan扫描的形式全部失效
 *      配置@ServletComponentScan后，DruidConfig和Web注解形式同时生效
 *      druid会对同一个web请求记录两次
 *
 * @see com.yuneke.config.DruidConfig
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
        })
@Deprecated
public class DruidStatFilter extends WebStatFilter {

}