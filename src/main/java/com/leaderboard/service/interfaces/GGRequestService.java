package com.leaderboard.service.interfaces;

import com.leaderboard.dto.client.gg.GGResultResponse;
import com.leaderboard.dto.client.gg.GroupsResponse;

import java.util.List;

public interface GGRequestService {
    String getHTMLBody(String url);
    List<GGResultResponse> promotionIdRequest(String url);
    GroupsResponse groupIdRequest(String promoUrl);

}
