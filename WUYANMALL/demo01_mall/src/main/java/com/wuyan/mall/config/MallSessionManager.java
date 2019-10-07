package com.wuyan.mall.config;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @Program: WUYANMALL
 * @Author: ZyEthan
 * @Description: 商城session管理器
 * @Date: 2019-10-04-14:18
 */
public class MallSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest servletRequest, ServletResponse response) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String header = request.getHeader("X-cskaoyanmall-Admin-Token");
        if (header != null && !"".equals(header)){
            return header;
        }
        return super.getSessionId(servletRequest, response);
    }
}
