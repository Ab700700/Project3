package com.example.project3bms.Service;

import com.example.project3bms.Api.ApiException;
import com.example.project3bms.DTO.CustomerDTO;
import com.example.project3bms.DTO.EmployeeDTO;
import com.example.project3bms.Model.Customer;
import com.example.project3bms.Model.Employee;
import com.example.project3bms.Model.User;
import com.example.project3bms.Repository.EmployeeRepository;
import com.example.project3bms.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public void addEmployee(EmployeeDTO employeeDTO){
        User newUser = new User(null,employeeDTO.getUsername(),employeeDTO.getPassword(),employeeDTO.getName(),employeeDTO.getEmail(),employeeDTO.getRole(),null,null);
        userRepository.save(newUser);
        Employee employee = new Employee(null,employeeDTO.getPosition(),employeeDTO.getSalary(),newUser);
        employeeRepository.save(employee);
    }
    public void register(EmployeeDTO employeeDTO){
        User user = new User(null,employeeDTO.getUsername(),employeeDTO.getPassword(),employeeDTO.getName(),employeeDTO.getEmail(),employeeDTO.getRole(),null,null);
        Employee employee = new Employee(null,employeeDTO.getPosition(),employeeDTO.getSalary(),user);
        String genPass= new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(genPass);
        userRepository.save(user);
        employeeRepository.save(employee);
    }

    public void updateEmployee(EmployeeDTO employeeDTO,Integer id){
        Employee oldEmployee = employeeRepository.findEmployeeById(id);
        if(oldEmployee == null) throw new ApiException("Employee not found");
        User user = userRepository.findUserById(id);
        if(user == null) throw new ApiException("User not found");
        oldEmployee.setUser(user);
        oldEmployee.setPosition(employeeDTO.getPosition());
        oldEmployee.setSalary(employeeDTO.getSalary());
        employeeRepository.save(oldEmployee);
    }

    public void deleteEmployee(Integer id){
        Employee employee = employeeRepository.findEmployeeById(id);
        if(employee == null) throw  new ApiException("Employee not found");
        User user = userRepository.findUserById(id);
        if(user == null) throw  new ApiException("User not found");
        user.setEmployee(null);
        userRepository.save(user);
        employeeRepository.delete(employee);
    }

}
