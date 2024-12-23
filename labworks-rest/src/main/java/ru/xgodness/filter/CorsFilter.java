package ru.xgodness.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(2)
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        if (servletRequest instanceof HttpServletRequest httpServletRequest &&
                httpServletRequest.getHeader("Origin") != null) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.addHeader("Access-Control-Allow-Methods",
                    "GET, POST, PUT, DELETE, PATCH, OPTIONS, HEAD");
            httpServletResponse.addHeader("Access-Control-Allow-Headers",
                    "X-Requested-With, Authorization, Accept-Version, Content-MD5, CSRF-Token, Content-Type");
            httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
