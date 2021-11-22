package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Accounts {


    private long accountId;
    private long userId;
    private BigDecimal balance;

    public Accounts(){

    }
    public Accounts(Long accountId, Long userId, BigDecimal balance){
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}