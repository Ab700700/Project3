package com.example.project3bms.Repository;

import com.example.project3bms.Model.Account;
import com.example.project3bms.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountById(Integer id);
    List<Account> findAccountsByCustomer(Customer customer);

}
