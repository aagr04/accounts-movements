package com.accounts.movements.dto;

import com.accounts.movements.util.AccountType;

import java.math.BigDecimal;

public record CreateAccountRequest(
        Long idCustomer,
        Long accountNumber,
        BigDecimal initialBalance,
        AccountType accountType,
        Boolean status
) {
}
