package com.example.project3bms.Controller;

import com.example.project3bms.Model.Account;
import com.example.project3bms.Model.User;
import com.example.project3bms.Service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bms/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/get")
    public ResponseEntity getAccounts(){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccounts());
    }
    @GetMapping("/account-details/{id}")
    public ResponseEntity accountDetails(@PathVariable Integer id , @AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.accountDetails(id,user.getId()));
    }
    @PostMapping("/create")
    public ResponseEntity createAccount(@AuthenticationPrincipal User user, @RequestBody @Valid Account account){
        accountService.creatAccount(user.getId(),account);
        return ResponseEntity.status(HttpStatus.OK).body("Account created");
    }
    @PutMapping("/update-account/{id}")
    public ResponseEntity updateAccount(@PathVariable Integer id, @AuthenticationPrincipal User user, @RequestBody @Valid Account account){
        accountService.updateAccount(id,user.getId(),account);
        return ResponseEntity.status(HttpStatus.OK).body("Account updated");
    }
    @DeleteMapping("/delete-account/{id}")
    public ResponseEntity deleteAccount(@PathVariable Integer id, @AuthenticationPrincipal User user){
        accountService.deleteAccount(id, user.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Account deleted");
    }
    @PutMapping("/withdraw/{id}/{amount}")
    public ResponseEntity withdraw(@PathVariable Integer id, @AuthenticationPrincipal User user, @PathVariable Double amount){
        accountService.withdraw(id,user.getId(),amount);
        return ResponseEntity.status(HttpStatus.OK).body("Done");
    }

    @PutMapping("/deposit/{id}/{amount}")
    public ResponseEntity deposit(@PathVariable Integer id, @AuthenticationPrincipal User user, @PathVariable Double amount){
        accountService.deposit(id,user.getId(),amount);
        return ResponseEntity.status(HttpStatus.OK).body("Done");
    }
    @PutMapping("/active/{id}")
    public ResponseEntity active(@PathVariable Integer id, @AuthenticationPrincipal User user){
        accountService.activeAccount(id,user.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Done");
    }
    @PutMapping("/block/{id}")
    public ResponseEntity block(@PathVariable Integer id, @AuthenticationPrincipal User user){
        accountService.blockAccount(id,user.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Done");
    }
    @PutMapping("/transfer-from/{first_account}/to/{secound_account}/{amount}")
    public ResponseEntity transfer(@PathVariable Integer first_account, @PathVariable Integer secound_account,@AuthenticationPrincipal User user,@PathVariable Double amount){
        accountService.transfer(first_account,secound_account,user.getId(),amount);
        return ResponseEntity.status(HttpStatus.OK).body("Done");
    }
    @GetMapping("/get-accounts")
    public ResponseEntity userAccounts(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.userAccounts(user.getId()));
    }


}
