package com.leaderboard.service;

import com.leaderboard.dto.api.response.ProviderDataResponse;
import com.leaderboard.dto.api.response.ProviderResponse;
import com.leaderboard.entity.Provider;
import com.leaderboard.service.interfaces.ProviderService;
import com.leaderboard.service.interfaces.ResultService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ResultService resultService;

    public ProviderServiceImpl(ResultService resultService) {
        this.resultService = resultService;
    }

    @Override
    public List<ProviderResponse> getProviders() {
        List<Provider> allProviders = resultService.getAllProviders();
        System.out.println(allProviders);
        return Collections.EMPTY_LIST;
    }

    @Override
    public ProviderDataResponse getProviderData(String provider) {
        return null;
    }
}
