package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Accounts;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class jdbcAccountsDAO implements AccountsDAO {
    private JdbcTemplate jdbcTemplate;

    public jdbcAccountsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public BigDecimal getAccountBalance(long userId) {
        String sql = "SELECT balance FROM accounts JOIN users USING (user_id) WHERE user_id = ?;";
        BigDecimal results = jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);
        return results;
    }


    public void updatingBalancesTransferred(long accountFrom, long accountTo){
            String sql = "UPDATE accounts SET balance = (SELECT balance FROM accounts " +
                    "JOIN transfers ON transfers.account_from = accounts.account_id WHERE account_from = ?;)" +
                    "- (SELECT amount FROM transfers WHERE account_from = ?;)";
        String sqlTWO = "UPDATE accounts SET balance = (SELECT balance FROM accounts " +
                "JOIN transfers ON transfers.account_to = accounts.account_id WHERE account_to = ?;)" +
                "+ (SELECT amount FROM transfers WHERE account_to = ?;)";
            jdbcTemplate.update(sql, accountFrom);
            jdbcTemplate.update(sqlTWO, accountTo);
        }
}
