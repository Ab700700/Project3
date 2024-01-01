package com.example.project3bms.Service;

import com.example.project3bms.Api.ApiException;
import com.example.project3bms.Model.User;
import com.example.project3bms.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }




    public void updateUser(User user , Integer id){
        User oldUser = userRepository.findUserById(id);
        if(oldUser == null) throw new ApiException("User not found");
        user.setId(id);
        user.setRole(oldUser.getRole());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public void delete(Integer id){
        User user = userRepository.findUserById(id);
        if(user == null) throw new ApiException("User not found");
        userRepository.delete(user);
    }

}
