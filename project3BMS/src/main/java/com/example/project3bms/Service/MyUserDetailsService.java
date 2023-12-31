package com.example.project3bms.Service;

import com.example.project3bms.Api.ApiException;
import com.example.project3bms.Model.User;
import com.example.project3bms.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findUserByUsername(username);
        if(user == null) throw new ApiException("Username or password went wrong");
        return user;
    }
}
