package com.example.project3bms.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {
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
    @NotEmpty(message = "Position should not be empty")
    private String position;
    @NotNull(message = "Salary should not be empty")
    @Positive(message = "Salary should not be a positive number")
    private Double salary;
}
