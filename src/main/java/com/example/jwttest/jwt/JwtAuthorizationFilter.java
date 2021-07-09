package com.example.jwttest.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwttest.auth.PrincipalDetails;
import com.example.jwttest.model.User;
import com.example.jwttest.model.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// basicAuthentication filter : 권한이나 인증이 필요한 특정 주소를 요청했을때 이 필터를 타게됨
// 권한이나 인증이 필요한 주소가 아니라면 이 필터를 안탄다
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    //인증이나 권한이 필요한 주소요청이 있을때 해당 필터를 타게됨
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("인증이나 권한이 필요한 주소입니다 basicAuthenticationFitler");
        String header = request.getHeader("Authorization");
        System.out.println("header:"+header);

        if(header ==null || !header.startsWith("Bearer")){
            chain.doFilter(request,response);
            return;
        }
        //jwt 토큰을 검증해서 정상적인 사용자인지 확인
        String token = request.getHeader("Authorization").replace("Bearer ","");
        String username = JWT.require(Algorithm.HMAC512("cos")).build().verify(token).getClaim("username").asString();
        //서명이 정상적으로 됨
        if(username !=null){
            User user = userRepository.findByUsername(username);

            PrincipalDetails principalDetails = new PrincipalDetails(user);
            //authentication 객체를 강제로 만듬 정상적인 이용자이기때문에 상관이없음
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            //강제로 시큐리티세션에 접근하여 authentication객체를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request,response);
        }
    }
}
