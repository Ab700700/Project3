package com.example.project3bms.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    private Integer userid;
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 4,max = 10, message = "Password should not be empty")
    private String username;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max=20, message = "Name should be between 2 characters and 20 characters")
    private String name;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty(message = "Role should not be empty")
    @Pattern(regexp = "CUSTOMER|EMPLOYEE|ADMIN",message = "Role should be customer, employee or admin")
    private String role;
    @Pattern(regexp = "05.[0-9]+", message = "Phone number should start with 05")
    private String phonenumber;
}
