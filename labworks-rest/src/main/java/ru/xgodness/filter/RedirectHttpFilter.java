package ru.xgodness.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log
@Component
@Order(3)
public class RedirectHttpFilter implements Filter {
    private static final int HTTPS_PORT = 5269;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest httpServletRequest
                && "http".equalsIgnoreCase(httpServletRequest.getScheme())) {
            String httpQuery = httpServletRequest.getQueryString();
            log.info("Redirecting HTTP request: " + httpServletRequest.getRequestURI() + (httpQuery == null ? "" : "?" + httpQuery));

            String httpsQuery = httpQuery == null ? "" : "?" + httpQuery;

            String httpsUri = "https://%s:%d%s".formatted(
                    httpServletRequest.getServerName(),
                    HTTPS_PORT,
                    httpServletRequest.getRequestURI() + httpsQuery);

            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.setStatus(307);
            httpServletResponse.addHeader("Location", httpsUri);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
