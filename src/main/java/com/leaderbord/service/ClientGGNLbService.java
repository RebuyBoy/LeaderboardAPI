package com.leaderbord.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leaderbord.converters.ResponseDtoConverter;
import com.leaderbord.dto.GGPlayerDTO;
import com.leaderbord.dto.response.GroupsResponseDTO;
import com.leaderbord.dto.response.ResponseDto;
import com.leaderbord.exceptions.NoResultException;
import com.leaderbord.util.AES;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class ClientGGNLbService {
    private static final String GGN_GROUP_ID_REQUEST_BASE = "https://pml.good-game-network.com/lapi/leaderboard/groups/";
    private static final String SECRET_KEY = "milliseconds";
    private static final String GGN_SHORT_DECK_PROMO_URL = "https://play.pokerok900.com/promotions/promo-short-deck";
    private RestTemplate restTemplate;
    private ResponseDtoConverter converter;
    private ObjectMapper objectMapper;

    public ClientGGNLbService(RestTemplate restTemplate, ResponseDtoConverter converter, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.converter = converter;
        this.objectMapper = objectMapper;
    }

    public List<GGPlayerDTO> promotionIdRequest(List<String> urls) throws JsonProcessingException {
        String url = "https://pml.good-game-network.com/lapi/leaderboard/59605/?hasSummary=true";
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String body = exchange.getBody();
        ResponseDto responseDto = objectMapper.readValue(body, ResponseDto.class);

        String secret = exchange.getHeaders().get("milliseconds").get(0);
        return null;
    }

    public GroupsResponseDTO groupIdRequest(String promoUrl) {
        //TODO validations
        ResponseEntity<String> mainResponse = restTemplate.exchange(promoUrl, HttpMethod.GET, null, String.class);
        String groupId = findGroupIdFromResponse(mainResponse.getBody());
        String ggnGroupRequest = GGN_GROUP_ID_REQUEST_BASE + groupId;
        try {
            ResponseEntity<ResponseDto> groupResponseWithPromotionIds = restTemplate.exchange(ggnGroupRequest, HttpMethod.GET, null, ResponseDto.class);
            //TODO check Nulls
            String secret = groupResponseWithPromotionIds.getHeaders().get(SECRET_KEY).get(0);
            String decrypt = AES.decrypt(secret, groupResponseWithPromotionIds.getBody().getData());
            return objectMapper.readValue(decrypt, GroupsResponseDTO.class);
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

//    public static void main(String[] args) {
//        ClientGGNLbService clientGGNLbService = new ClientGGNLbService(new RestTemplate(), new ResponseDtoConverter(), new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
//        groupsResponseDTO = clientGGNLbService.groupIdRequest(GGN_SHORT_DECK_PROMO_URL);
//        System.out.println(groupsResponseDTO.getGroupId());
//        System.out.println(Arrays.toString(groupsResponseDTO.getGameTypes()));
//        groupsResponseDTO.getSets().stream().forEach(setsDTO -> {
//            System.out.println(setsDTO.getDate());
//            setsDTO.getSubsets().stream().forEach(e -> System.out.println(e));
//        });
//    }

//    private List<String> generateListUrls(List<SetsDTO> promotionIds) {
//        List<String> promoUrls = new ArrayList<>();
//        promotionIds.stream().filter(promotion-> promotion.getDate())
//    }
//
//    private String generateUrl(String promoId, String stake) {
//        return String.format("https://pml.good-game-network.com/lapi/leaderboard/%s/?status=PENDING&status=OPTED_IN&status=COMPLETED&status=EXPIRED&limit=%s&hasSummary=true&hasSummaryPaidPrizes=true&hasSummaryPrizeItem=true", promoId, stake);
//    }
//
//    private boolean compareDate(String date){
//
//    }
}
