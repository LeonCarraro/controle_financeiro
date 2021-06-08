package com.leoncarraro.controlefinanceiro.api.cors;

import com.leoncarraro.controlefinanceiro.api.config.property.APIProperty;
import lombok.AllArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@AllArgsConstructor
public class CorsFilter implements Filter {

    private final APIProperty apiProperty;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("Access-Control-Allow-Origin", apiProperty.getSecurity().getAllowedOrigin());
        res.setHeader("Access-Control-Allow-Credentials", "true");

        if (req.getMethod().equals("OPTIONS") &&
                apiProperty.getSecurity().getAllowedOrigin().equals(req.getHeader("Origin"))) {

            res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
            res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            res.setHeader("Access-Control-Max-Age", "3600");

            res.setStatus(HttpStatus.OK.value());
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}
