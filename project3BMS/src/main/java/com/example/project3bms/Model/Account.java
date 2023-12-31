package com.example.project3bms.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Account number should not be empty")
    @Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}")
    @Column(columnDefinition = "varchar(30) not null")
    private String accountNumber;
    @NotEmpty(message = "Balance should not be empty")
    @Positive(message = "Balance should be positive number")
    @Column(columnDefinition = "double not null")
    private Double balance;
    @Value("false")
    @Column(columnDefinition = "boolean")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;
}
