package com.accounts.movements.entity;

import com.accounts.movements.util.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("id_customer")
    private Long idCustomer;

    @Column(unique = true, nullable = false, length = 12)
    @JsonProperty("account_number")
    private Long accountNumber;

    @Column(precision = 10, scale = 2)
    @JsonProperty("initial_balance")
    private BigDecimal initialBalance;

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    @JsonProperty("account_type")
    private AccountType accountType;

    @JsonProperty("use_balance")
    private Boolean useBalance;

    @JsonProperty("status")
    private Boolean status;

    public Boolean getUseBalance() {
        return useBalance;
    }

    public void setUseBalance(Boolean useBalance) {
        this.useBalance = useBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
