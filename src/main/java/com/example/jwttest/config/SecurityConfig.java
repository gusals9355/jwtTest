package com.example.jwttest.config;

import com.example.jwttest.filter.MyFilter1;
import com.example.jwttest.filter.MyFilter2;
import com.example.jwttest.jwt.JwtAuthenticationFilter;
import com.example.jwttest.jwt.JwtAuthorizationFilter;
import com.example.jwttest.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.And;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .httpBasic()

            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 사용 X

            .and()
            .authorizeRequests()
            .antMatchers("/user/**").authenticated()
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll()

            .and()
            .addFilterBefore(new MyFilter1(), SecurityContextPersistenceFilter.class)
            .addFilter(corsFilter)
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))

            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/home");
    }
}
