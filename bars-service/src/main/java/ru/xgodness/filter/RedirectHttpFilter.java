package ru.xgodness.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;

import java.io.IOException;

@Log
@WebFilter(urlPatterns = {"/*"})
public class RedirectHttpFilter implements Filter {
    private static final String HTTPS_PORT = ":9677";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest httpServletRequest
                && "http".equalsIgnoreCase(httpServletRequest.getScheme())) {
            String httpQuery = httpServletRequest.getQueryString();
            log.info("Redirecting HTTP request: " + httpServletRequest.getRequestURI() + (httpQuery == null ? "" : "?" + httpQuery));

            String httpsQuery = httpQuery == null ? "" : "?" + httpQuery;

            String httpsUri = "https://" + httpServletRequest.getServerName() + HTTPS_PORT + httpServletRequest.getRequestURI() + httpsQuery;

            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.setStatus(307);
            httpServletResponse.addHeader("Location", httpsUri);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
