package br.com.naodeixesobrar.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.naodeixesobrar.jwt.JWTUtil;

import java.io.IOException;

public class JWTFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if(req.getRequestURI().contains("/rest/user/login")){ //TODO trocar por startsWith
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = req.getHeader(JWTUtil.TOKEN_HEADER);

        if(token == null || token.trim().isEmpty()){ 
            res.setStatus(401);
            return;
        }

        try {
            Jws<Claims> parser = JWTUtil.decode(token);
            System.out.println("User request: "+ parser.getBody().getSubject());
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (SignatureException e) {
            res.setStatus(401);
        }

    }

    @Override
    public void destroy() {}
}