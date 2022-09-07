package com.leaderboard.service.interfaces;

import com.leaderboard.dto.GGResultDTO;
import com.leaderboard.dto.response.GroupsResponse;

import java.util.List;

public interface RequestService {
    String getHTMLBody(String url);
    List<GGResultDTO> promotionIdRequest(String url);
    GroupsResponse groupIdRequest(String promoUrl);

}
