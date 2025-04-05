package com.accounts.movements.service.impl;

import com.accounts.movements.dto.MessageExceptionDto;
import com.accounts.movements.entity.Account;
import com.accounts.movements.exception.UserManagementException;
import com.accounts.movements.repository.AccountRepository;
import com.accounts.movements.service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(Account account) {
        account.setUseBalance(false);
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(
                () -> new UserManagementException(
                        new MessageExceptionDto(
                                String.format("ACCOUNT_ID[%d]_NOT_FOUND", accountId),
                                HttpStatus.BAD_REQUEST
                        ), HttpStatus.BAD_REQUEST
                )
        );
    }

    @Override
    public Account findByAccountNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

}
