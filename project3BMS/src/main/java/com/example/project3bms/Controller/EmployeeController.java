package com.example.project3bms.Controller;

import com.example.project3bms.DTO.CustomerDTO;
import com.example.project3bms.DTO.EmployeeDTO;
import com.example.project3bms.Model.User;
import com.example.project3bms.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bms/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity getEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployees());
    }

    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.addEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Employee added");
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.register(employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Registered");
    }
    @PutMapping("/update")
    public ResponseEntity updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO, @AuthenticationPrincipal User user){
        employeeService.updateEmployee(employeeDTO,user.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Employee updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal User user){
        employeeService.deleteEmployee(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted");
    }
}
