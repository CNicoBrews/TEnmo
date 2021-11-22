package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.dao.AccountsDAO;
import com.techelevator.tenmo.model.LoginDTO;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountsController {
    private AccountsDAO accountsDao;

    public AccountsController(AccountsDAO accountsDao) {
        this.accountsDao = accountsDao;
    }

    @RequestMapping(path = "/accounts/{accountsId}", method = RequestMethod.GET)
    public BigDecimal getBalance(@PathVariable("accountsId") Long userId){
        return accountsDao.getAccountBalance(userId);
    }

    @RequestMapping(path = "/accounts/{accountsId}", method = RequestMethod.PUT)
    public void updatingFromBalanceTransfer(long accountFrom, long accountTo){
        accountsDao.updatingBalancesTransferred(accountFrom, accountTo);
    }

    }
