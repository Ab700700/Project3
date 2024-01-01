package com.example.project3bms.Controller;

import com.example.project3bms.Model.User;
import com.example.project3bms.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bms/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user){
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User added");
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody @Valid User user, @AuthenticationPrincipal User auth){
        userService.updateUser(user,auth.getId());
        return ResponseEntity.status(HttpStatus.OK).body("User updated");
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal User user){
        userService.delete(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }

}
