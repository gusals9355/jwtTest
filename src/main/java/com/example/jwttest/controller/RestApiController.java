package com.example.jwttest.controller;

import com.example.jwttest.auth.PrincipalDetails;
import com.example.jwttest.model.User;
import com.example.jwttest.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestApiController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/home")
    public String home(){
//        System.out.println(principalDetails.getUser());
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/token")
    @ResponseBody
    public String token(@AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println(principalDetails);
        return "<h1>token</h1>";
    }

    @GetMapping("/join")
    public void join(){

    }

    @PostMapping("/join")
    public void joinP(User user){
        System.out.println(user.getPassword());
        String hashPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPw);
        userRepository.save(user);
    }
    @GetMapping("/user")
    public void user(){

    }
    @GetMapping("/admin")
    public void admin(){

    }
}