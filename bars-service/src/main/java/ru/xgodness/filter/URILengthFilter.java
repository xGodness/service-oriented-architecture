package ru.xgodness.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import ru.xgodness.exception.dto.ErrorMessages;

import java.io.IOException;
import java.io.PrintWriter;

@Log
@WebFilter(urlPatterns = {"/*"})
public class URILengthFilter implements Filter {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ErrorMessages errorMessage = new ErrorMessages("Request URI is too long");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Filtering: checking URI length");

        if (servletRequest instanceof HttpServletRequest httpServletRequest) {
            String uriWithQuery = "%s?%s".formatted(httpServletRequest.getRequestURI(), httpServletRequest.getQueryString());

            if (uriWithQuery.length() > 1024) {
                log.info("Filtering: aborting request, URI too long");

                servletResponse.setContentType("application/json;charset=UTF-8");
                ((HttpServletResponse) servletResponse).setStatus(414);
                PrintWriter out = servletResponse.getWriter();
                out.print(mapper.writeValueAsString(errorMessage));
                out.flush();
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}