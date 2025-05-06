package com.example.Graduation.Project.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class HttpLoggingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(HttpLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        long startTime = System.currentTimeMillis();

        // Log request
        log.info("=> {} {} from {} (Content-Type: {})",
                req.getMethod(),
                req.getRequestURI(),
                req.getRemoteAddr(),
                req.getContentType());

        chain.doFilter(request, response);

        // Log response
        log.info("<= {} {} - Status: {} ({} ms)",
                req.getMethod(),
                req.getRequestURI(),
                res.getStatus(),
                System.currentTimeMillis() - startTime);
    }
}