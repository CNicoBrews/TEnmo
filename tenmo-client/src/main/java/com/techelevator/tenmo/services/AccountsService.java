package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfers;
import com.techelevator.tenmo.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public class AccountsService {

    private final String API_BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();

    public AccountsService(String apiURL){
        API_BASE_URL = apiURL;
    }

    public  List<User> viewUsersList (){
        String url = API_BASE_URL + "/users/";
        ResponseEntity<List<User>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        return response.getBody();
    }

    public BigDecimal getBalance(AuthenticatedUser user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + user.getToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = API_BASE_URL + "accounts/" + user.getUser().getId();
        return restTemplate.exchange(url, HttpMethod.GET, entity, BigDecimal.class).getBody();
    }

    public Transfers transferHistory(Integer id) {
        return null;
    }
}


