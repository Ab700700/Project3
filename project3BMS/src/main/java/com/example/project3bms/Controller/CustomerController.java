package com.example.project3bms.Controller;

import com.example.project3bms.DTO.CustomerDTO;
import com.example.project3bms.Model.User;
import com.example.project3bms.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bms/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get-customers")
    public ResponseEntity getCustomers(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomers());
    }
    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        customerService.addCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Customer added");
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CustomerDTO customerDTO){
        customerService.register(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Registered");
    }
    @PutMapping("/update")
    public ResponseEntity updateCustomer(@RequestBody @Valid CustomerDTO customerDTO, @AuthenticationPrincipal User user){
        customerService.updateCustomer(customerDTO,user.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Customer updated");
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user){
        customerService.deleteCustomer(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted");
    }

}
