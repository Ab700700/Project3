package com.example.project3bms.Service;

import com.example.project3bms.Api.ApiException;
import com.example.project3bms.DTO.CustomerDTO;
import com.example.project3bms.Model.Customer;
import com.example.project3bms.Model.User;
import com.example.project3bms.Repository.CustomerRepository;
import com.example.project3bms.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

        public void addCustomer(CustomerDTO customerDTO){
            User user = userRepository.findUserById(customerDTO.getUserid());
            if(user == null || !user.getRole().equals("CUSTOMER")) throw new ApiException("User not found");
            Customer customer = new Customer(null,customerDTO.getPhonenumber(),user,null);
            customerRepository.save(customer);
        }

        public List<Customer> getCustomers(){
            return customerRepository.findAll();
        }

        public void updateCustomer(CustomerDTO customerDTO, Integer id){
            Customer oldCustomer = customerRepository.findCustomerById(id);
            if(oldCustomer == null) throw new ApiException("Customer not found");
            User user = userRepository.findUserById(id);
            if(user == null) throw new ApiException("User not found");
            oldCustomer.setPhonenumber(customerDTO.getPhonenumber());
            oldCustomer.setUser(user);
            customerRepository.save(oldCustomer);
        }

        public void deleteCustomer(Integer id){
            Customer customer = customerRepository.findCustomerById(id);
            if(customer == null) throw new ApiException("Customer not found");
            User user = userRepository.findUserById(id);
            if(user == null) throw new ApiException("User not found");
            user.setCustomer(null);
            userRepository.save(user);
            customerRepository.delete(customer);
        }
}
