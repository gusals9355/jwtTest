package com.example.jwttest.jwt;

public interface JwtProperties {
    String SECRET = "mndc"; //우리 서버만 알고 있는 비밀값
    int EXPIRATTION_TIME = 8640000; //(1000 * 60 * 60 * 24) 24시간
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
