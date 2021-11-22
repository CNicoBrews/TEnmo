package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class jdbcTransfersDAO implements TransfersDAO {
    private final JdbcTemplate jdbcTemplate;

    public jdbcTransfersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transfers getTransfer(long transferId) {
        Transfers transfers = null;
        String sql = "SELECT * FROM transfers WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        if (results.next()) {
            transfers = mapRowToTransfers(results);
        }
        return transfers;
    }

    @Override
    public List<Transfers> transfersHistory(Long user_id) {
        List<Transfers> previousTransfers = new ArrayList<>();
        String sql = "SELECT * FROM transfers JOIN account USING (account_id) WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);
        while (results.next()) {
            previousTransfers.add(mapRowToTransfers(results));
        }
        return previousTransfers;
    }

    @Override
    public Transfers createTransfer(Transfers transfers) {
        String sql = "INSERT INTO transfers (account_from, account_to, amount) " +
                "VALUES (?, ?, ?);";
        Long newId = jdbcTemplate.queryForObject(sql, Long.class,
                 transfers.getAccountFrom(), transfers.getAccountTo(),
                transfers.getAmount());
        return getTransfer(newId);
    }



    public Transfers mapRowToTransfers(SqlRowSet rowSet) {
        Transfers transfers = new Transfers();
        transfers.setTransferId(rowSet.getLong("transfer_id"));
        transfers.setTransferTypeId(rowSet.getLong("transfer_type_id"));
        transfers.setTransferStatusId(rowSet.getLong("transfer_status_id"));
        transfers.setAccountFrom(rowSet.getLong("account_from"));
        transfers.setAccountTo(rowSet.getLong("account_to"));
        transfers.setAmount(rowSet.getBigDecimal("amount"));
        return transfers;
    }


}








