package com.wonder.web;

import com.wonder.entity.User;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter {
    //声明spring容器引用
    private WebApplicationContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if(user==null){
            response.sendRedirect("login.html");
            return;
        }else {
            filterChain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {

    }
}
