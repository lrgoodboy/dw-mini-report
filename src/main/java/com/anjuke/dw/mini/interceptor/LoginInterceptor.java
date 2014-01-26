package com.anjuke.dw.mini.interceptor;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriComponentsBuilder;

import com.anjuke.dw.mini.model.AnjukeUser;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    @Qualifier("commonProperties")
    private Properties commonProperties;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        if (session.getAttribute("currentUser") instanceof AnjukeUser) {
            return true;
        }

        response.sendRedirect(UriComponentsBuilder
                .fromPath(request.getContextPath())
                .path("/login")
                .queryParam("from", getUrl(request))
                .build().toUriString());

        return false;
    }

    private String getUrl(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder(request.getRequestURL());
        String queryString = request.getQueryString();
        if (queryString != null) {
            sb.append("?").append(queryString);
        }
        return sb.toString();
    }

}
