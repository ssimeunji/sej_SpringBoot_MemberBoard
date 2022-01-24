package com.icia.sej.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();

        if (session.getAttribute("loginId")==null) {
            session.setAttribute("redirectURL", requestURI);
            response.sendRedirect("/");
            return false;
        } else {
            return true;
        }
    }
}
