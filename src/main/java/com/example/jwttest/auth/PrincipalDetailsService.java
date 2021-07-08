package com.example.jwttest.auth;

import com.example.jwttest.model.User;
import com.example.jwttest.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUser");
        System.out.println(username);
        User user = userRepository.findByUsername(username);
        System.out.println(user);
        if(user != null){
            return new PrincipalDetails(user);
        }
        return null;
    }
}
