package com.leaderbord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class ClientGGNLbService {
    private RestTemplate restTemplate;

    public ClientGGNLbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sendRequest(String url, HttpMethod request){
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, request, null, String.class);
        String key = responseEntity.getHeaders().get("milliseconds").get(0);
        return responseEntity.getBody();
    }
}
