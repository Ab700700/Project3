package com.example.project3bms.Service;

import com.example.project3bms.Api.ApiException;
import com.example.project3bms.Model.Account;
import com.example.project3bms.Model.Customer;
import com.example.project3bms.Model.User;
import com.example.project3bms.Repository.AccountRepository;
import com.example.project3bms.Repository.CustomerRepository;
import com.example.project3bms.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account accountDetails(Integer id, Integer userid) {
        Account account = accountRepository.findAccountById(id);
        if (account == null) throw new ApiException("Account not found");
        return account;
    }

    public void creatAccount(Integer userid, Account account) {
        Customer customer = customerRepository.findCustomerById(userid);
        account.setCustomer(customer);
        accountRepository.save(account);
    }

    public void updateAccount(Integer id, Integer userid, Account newAccount) {
        Account oldAccount = accountRepository.findAccountById(id);
        if (oldAccount == null) throw new ApiException("Account not found");
        if (!oldAccount.getCustomer().getId().equals(userid)) throw new ApiException("User not allowed to update");
        newAccount.setId(id);
        newAccount.setCustomer(customerRepository.findCustomerById(userid));
        accountRepository.save(newAccount);
    }


    public void deleteAccount(Integer id, Integer userid) {
        Account account = accountRepository.findAccountById(id);
        if (account == null) throw new ApiException("Account not found");
        if (!account.getCustomer().getId().equals(userid))
            throw new ApiException("User not allowed to delete this account");
        accountRepository.delete(account);
    }

    public void withdraw(Integer id, Integer userid, Double amount) {
        Account account = accountRepository.findAccountById(id);
        if (account == null) throw new ApiException("Account not found");
        if (!account.getCustomer().getId().equals(userid)) throw new ApiException("User not allowed to withdraw");
        if (!account.getIsActive()) throw new ApiException("Account is not active");
        if (account.getBalance() < amount) throw new ApiException("Balance is not enough to withdraw");
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    public void deposit(Integer id, Integer userid, Double amount) {
        Account account = accountRepository.findAccountById(id);
        if (account == null) throw new ApiException("Account not found");
        if (!account.getCustomer().getId().equals(userid)) throw new ApiException("Account not found");
        if (!account.getIsActive()) throw new ApiException("Account is not active");
        account.setBalance(amount + account.getBalance());
        accountRepository.save(account);
    }


    public void activeAccount(Integer id,Integer userid){
        Account account = accountRepository.findAccountById(id);
        if(account == null) throw new ApiException("Account not found");
        User user = userRepository.findUserById(userid);
        if(user.getRole().equals("CUSTOMER")) throw new ApiException("User not allowed to active");
        if(account.getIsActive())throw new ApiException("Account is already activated");
        account.setIsActive(true);
        accountRepository.save(account);
    }

    public void blockAccount(Integer id , Integer userid){
        Account account = accountRepository.findAccountById(id);
        if(account == null) throw new ApiException("Account not found");
        User user = userRepository.findUserById(userid);
        if(user == null|| user.getRole().equals("CUSTOMER")) throw new ApiException("User not found or is not allowed to block");
        if(!account.getIsActive()) throw new ApiException("Account is not active");
        account.setIsActive(false);
        accountRepository.save(account);

    }

    public void transfer(Integer faccount, Integer saccount, Integer userid, Double amount){
        Account firstAccount = accountRepository.findAccountById(faccount);
        Account secoundAccount = accountRepository.findAccountById(saccount);
        if(firstAccount == null) throw new ApiException("Account not found");
        if(secoundAccount== null) throw new ApiException("Account not found");
        User user = userRepository.findUserById(userid);
        if(user == null) throw  new ApiException("User not found");
        if(!firstAccount.getCustomer().equals(user)) throw new ApiException("User not allowed to transfer");
        if(firstAccount.getBalance()<amount) throw new ApiException("Balance is not enough");
        firstAccount.setBalance(firstAccount.getBalance()-amount);
        secoundAccount.setBalance(secoundAccount.getBalance()+amount);
        accountRepository.save(firstAccount);
        accountRepository.save(secoundAccount);
    }

    public List<Account> userAccounts(Integer userid){
        Customer customer = customerRepository.findCustomerById(userid);
        return accountRepository.findAccountsByCustomer(customer);
    }

}
