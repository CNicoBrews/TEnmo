package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransfersDAO;
import com.techelevator.tenmo.dao.jdbcTransfersDAO;
import com.techelevator.tenmo.model.Transfers;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
@RestController
public class TransfersController {
    private TransfersDAO transfersDao;

    public TransfersController(TransfersDAO transfersDao) {
        this.transfersDao = transfersDao;
    }



    @RequestMapping(value =  "transfers", method = RequestMethod.POST)
    public Transfers createTransfer(@RequestBody Transfers transfers ){

        return transfersDao.createTransfer(transfers);

        }
    @RequestMapping(value = "/transfers/{transferId}", method = RequestMethod.GET)
        public  Transfers getTransfer(@PathVariable long transferId) {
            return transfersDao.getTransfer(transferId);
    }
    @RequestMapping(value = "/transfers/history{Id}", method = RequestMethod.GET)
    public List<Transfers> transferHistory(@PathVariable long id) {
        return transfersDao.transfersHistory(id);
    }

   /* public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public Long getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(Long transferTypeId) {
        this.transferTypeId = transferTypeId;
    }
    public Long getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(Long transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public Long getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Long accountFrom) {
        this.accountFrom = accountFrom;
    }
    public Long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Long accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }*/
}
