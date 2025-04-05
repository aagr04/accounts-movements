package com.accounts.movements.dto;

import java.math.BigDecimal;

public record EventMessageCustomer(
        Long idCustomer,
        BigDecimal initialBalance,
        String event
) {
}
