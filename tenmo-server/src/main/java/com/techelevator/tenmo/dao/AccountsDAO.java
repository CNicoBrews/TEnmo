package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Accounts;

import java.math.BigDecimal;

public interface AccountsDAO {


    void updatingBalancesTransferred(long accountFrom, long accountTo);

    BigDecimal getAccountBalance(long userId);


}


