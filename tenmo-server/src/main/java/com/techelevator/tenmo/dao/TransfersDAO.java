package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransfersDAO {
    Transfers getTransfer(long transferId);


    List<Transfers> transfersHistory(Long user_id);

    Transfers createTransfer(Transfers transfers);




}
