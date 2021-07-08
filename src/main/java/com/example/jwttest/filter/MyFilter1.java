package com.example.jwttest.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter1 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if(req.getMethod().equals("POST")){
            System.out.println("post요청됨");
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);
            System.out.println("필터1");
            if(headerAuth != null && headerAuth.equals("cos")){
                filterChain.doFilter(req,res);
                return;
            }else{
                res.sendRedirect("/login");
                System.out.println("실패");
                return;
            }
        }else{
            filterChain.doFilter(req,res);
            return;
        }
    }
}
