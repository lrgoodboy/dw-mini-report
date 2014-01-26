package com.anjuke.dw.mini.interceptor;

import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriComponentsBuilder;

import com.anjuke.dw.mini.model.AnjukeUser;
import com.anjuke.dw.mini.service.LoginService;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    @Qualifier("commonProperties")
    private Properties commonProperties;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

        String cookieName = commonProperties.getProperty("auth.cookie.name");

        HttpSession session = request.getSession();
        if (session.getAttribute(LoginService.SESSION_USER) instanceof AnjukeUser) {
            return true;
        }

        String cookieValue = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(cookieName)) {
                cookieValue = cookie.getValue();
            }
        }

        if (cookieValue != null) {
            session.setAttribute(LoginService.SESSION_USER, new AnjukeUser(cookieValue));
            return true;
        }

        response.sendRedirect(UriComponentsBuilder.fromPath("/login")
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
