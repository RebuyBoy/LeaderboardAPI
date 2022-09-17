package com.leaderboard.service.interfaces;

import com.leaderboard.dto.api.response.ProviderDataResponse;
import com.leaderboard.dto.api.response.ProviderResponse;

import java.util.List;

public interface ProviderService {

    List<ProviderResponse> getProviders();

    ProviderDataResponse getProviderData(String provider);

}
