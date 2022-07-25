package com.leaderbord.service.interfaces;

import com.leaderbord.dto.GGPlayerResultDTO;
import com.leaderbord.dto.response.GroupsResponseDTO;

import java.util.List;

public interface RequestService {
    List<GGPlayerResultDTO> promotionIdRequest(String url);
    GroupsResponseDTO groupIdRequest(String promoUrl);
}
