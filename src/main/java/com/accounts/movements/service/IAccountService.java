package com.accounts.movements.service;

import com.accounts.movements.entity.Account;

import java.util.List;

public interface IAccountService {

    Account create(Account account);

    Account update(Account account);

    List<Account> findAll();

    Account findByAccountNumber(Long accountNumber);

    Account findById(Long accountId);

    void delete(Long id);

}
