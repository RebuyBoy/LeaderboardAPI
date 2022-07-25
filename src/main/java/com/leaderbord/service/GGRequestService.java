package com.leaderbord.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leaderbord.dto.GGPlayerResultDTO;
import com.leaderbord.dto.response.GroupsResponseDTO;
import com.leaderbord.dto.response.ResponseDTO;
import com.leaderbord.exceptions.NoResultException;
import com.leaderbord.service.interfaces.RequestService;
import com.leaderbord.util.AES;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GGRequestService implements RequestService {
    private static final String GGN_GROUP_ID_REQUEST_BASE = "https://pml.good-game-network.com/lapi/leaderboard/groups/";
    private static final String SECRET_KEY = "milliseconds";
    private static final String GGN_SHORT_DECK_PROMO_URL = "https://play.pokerok900.com/promotions/promo-short-deck";
    private static final String PromoUrlFormat = "https://pml.good-game-network.com/lapi/leaderboard/%s/?status=PENDING&status=OPTED_IN&status=COMPLETED&status=EXPIRED&limit=%s&hasSummary=true&hasSummaryPaidPrizes=true&hasSummaryPrizeItem=true";
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public GGRequestService(ObjectMapper mapper) {
        this.restTemplate = new RestTemplate();
        this.mapper = mapper;
    }

    @Override
    public List<GGPlayerResultDTO> promotionIdRequest(String url) {
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
    public GroupsResponseDTO groupIdRequest(String promoUrl) {
        //TODO validations
        //TODO razdelit' na dva  getGROUP  requestGroup
        ResponseEntity<String> mainResponse = restTemplate.exchange(promoUrl, HttpMethod.GET, null, String.class);
        String groupId = findGroupIdFromResponse(mainResponse.getBody());
        String ggnGroupRequest = GGN_GROUP_ID_REQUEST_BASE + groupId;
        try {
            ResponseEntity<ResponseDTO> groupResponseWithPromotionIds = restTemplate.exchange(ggnGroupRequest, HttpMethod.GET, null, ResponseDTO.class);

            //TODO check Nulls
            String secret = Objects.requireNonNull(groupResponseWithPromotionIds.getHeaders().get(SECRET_KEY)).get(0);
            String decrypt = AES.decrypt(secret, Objects.requireNonNull(groupResponseWithPromotionIds.getBody()).getData());
            return mapper.readValue(decrypt, GroupsResponseDTO.class);
        } catch (JsonProcessingException | NullPointerException e) {
            e.printStackTrace();
        }
        throw new NoResultException(String.format("By groupId %s data not found", groupId));
    }

    private String findGroupIdFromResponse(String response) {
        Matcher matcher = Pattern.compile("groupId=(\\d+)").matcher(response);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new NoResultException("Group id not found");
    }
}
