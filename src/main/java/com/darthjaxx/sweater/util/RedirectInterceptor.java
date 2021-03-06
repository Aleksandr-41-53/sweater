package com.darthjaxx.sweater.util;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RedirectInterceptor extends HandlerInterceptorAdapter {
    // add for turbolinks
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String args = request.getQueryString() != null ? request.getQueryString() : "";
            String url = request.getRequestURI().toString();
            if (!"".equals(args)) url = url  + "?" + args;
            response.setHeader("Turbolinks-Location", url);
        }
    }
}
