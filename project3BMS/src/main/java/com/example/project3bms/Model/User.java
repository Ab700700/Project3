package com.example.project3bms.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 4,max = 10, message = "Password should not be empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String username;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 6, message = "Password should be at least 6 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max=20, message = "Name should be between 2 characters and 20 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Column(columnDefinition = "varchar(30) not null")
    private String email;
    @NotEmpty(message = "Role should not be empty")
    @Pattern(regexp = "CUSTOMER|EMPLOYEE|ADMIN",message = "Role should be customer, employee or admin")
    @Column(columnDefinition = "varchar(9) not null check(role='CUSTOMER' or role = 'EMPLOYEE' or role ='ADMIN')")
    private String role;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
