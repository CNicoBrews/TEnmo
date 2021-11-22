package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfers;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TransferService {
    private final String API_BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();

    public TransferService(String apiURL) {
        this.API_BASE_URL = apiURL;
    }

    public Transfers getTransfer(long transferId)
    {
        String url = API_BASE_URL + "/transfers/" + transferId;
        return restTemplate.getForObject(url, Transfers.class);
    }
    public List<Transfers> transferHistory() {
        String url = API_BASE_URL + "/transfers/";
        ResponseEntity<List<Transfers>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Transfers>>() {});
        return response.getBody() ;

    }
    public Transfers createTransfer(Transfers transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfers> entity = new HttpEntity<>(transfer, headers);
        String url = API_BASE_URL + "/transfers/";
        ResponseEntity<Transfers> result = restTemplate.exchange(url, HttpMethod.POST, entity, Transfers.class);
        return result.getBody();
    }
}
