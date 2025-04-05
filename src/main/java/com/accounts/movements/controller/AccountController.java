package com.accounts.movements.controller;

import com.accounts.movements.entity.Account;
import com.accounts.movements.service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    private IAccountService iAccountService;

    public AccountController(IAccountService iAccountService){
        this.iAccountService = iAccountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody Account account) {
        return iAccountService.create(account);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account update(@RequestBody Account account) {
        return iAccountService.update(account);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Account> findAll() {
        return iAccountService.findAll();
    }

    @GetMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account findByAccountNumber(@PathVariable Long accountNumber) {
        return iAccountService.findByAccountNumber(accountNumber);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable Long id) {
        iAccountService.delete(id);
    }

}
