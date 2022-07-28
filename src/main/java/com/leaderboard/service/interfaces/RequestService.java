package com.leaderboard.service.interfaces;

import com.leaderboard.dto.GGResultDTO;
import com.leaderboard.dto.response.GroupsResponseDTO;

import java.util.List;

public interface RequestService {
    String mainPromoRequest(String url);
    List<GGResultDTO> promotionIdRequest(String url);
    GroupsResponseDTO groupIdRequest(String promoUrl);
}
