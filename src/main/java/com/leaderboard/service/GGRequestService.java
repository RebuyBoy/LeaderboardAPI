package com.leaderboard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leaderboard.dto.GGResultDTO;
import com.leaderboard.dto.response.GroupsResponseDTO;
import com.leaderboard.dto.response.ResponseDTO;
import com.leaderboard.exceptions.NoResultException;
import com.leaderboard.service.interfaces.RequestService;
import com.leaderboard.util.AES;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static com.leaderboard.constants.Constants.SECRET_KEY;

@Service
public class GGRequestService implements RequestService {
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public GGRequestService(ObjectMapper mapper) {
        this.restTemplate = new RestTemplate();
        this.mapper = mapper;
    }

    @Override
    public List<GGResultDTO> promotionIdRequest(String url) {
        try {
            ResponseEntity<ResponseDTO> promotionIdResponse = restTemplate.exchange(url, HttpMethod.GET, null, ResponseDTO.class);
            String secret = promotionIdResponse.getHeaders().get(SECRET_KEY).get(0);
            String decrypt = AES.decrypt(secret, promotionIdResponse.getBody().getData());
            return mapper.readValue(decrypt, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        throw new NoResultException("Request failed url: " + url);
    }

    @Override
    public String mainPromoRequest(String url) {
        ResponseEntity<String> mainResponse = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return mainResponse.getBody();
    }

    @Override
    public GroupsResponseDTO groupIdRequest(String url) {
        //TODO validations
        try {
            ResponseEntity<ResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, null, ResponseDTO.class);
            String secret = Objects.requireNonNull(response.getHeaders().get(SECRET_KEY)).get(0);
            String decrypt = AES.decrypt(secret, Objects.requireNonNull(response.getBody()).getData());
            return mapper.readValue(decrypt, GroupsResponseDTO.class);
        } catch (JsonProcessingException | NullPointerException e) {
            e.printStackTrace();
        }
        throw new NoResultException("groupIdRequest failed");
    }


}
