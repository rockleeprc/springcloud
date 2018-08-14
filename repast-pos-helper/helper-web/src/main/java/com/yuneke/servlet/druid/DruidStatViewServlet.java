package com.yuneke.servlet.druid;


import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * http://localhost:8080/druid/index.html
 */
@WebServlet(urlPatterns = "/druid/*",
        initParams={
                @WebInitParam(name="loginUsername",value="admin"),
                @WebInitParam(name="loginPassword",value="geek"),
                @WebInitParam(name="resetEnable",value="false")
        })
@Deprecated
public class DruidStatViewServlet extends StatViewServlet {
}