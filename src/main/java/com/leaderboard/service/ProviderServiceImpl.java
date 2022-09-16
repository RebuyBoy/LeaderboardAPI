package com.leaderboard.service;

import com.leaderboard.dto.api.response.ProviderData;
import com.leaderboard.dto.api.response.ProviderDataResponse;
import com.leaderboard.dto.api.response.ProviderResponse;
import com.leaderboard.dto.api.response.StakeResponse;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Stake;
import com.leaderboard.service.interfaces.ProviderService;
import com.leaderboard.service.interfaces.ResultService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ResultService resultService;

    public ProviderServiceImpl(ResultService resultService) {
        this.resultService = resultService;
    }

    @Override
    public List<ProviderResponse> getProviders() {
        return resultService.getAllProviders()
                .stream()
                .map(allProvider -> new ProviderResponse(allProvider.name(), allProvider.getDescription()))
                .toList();
    }

    @Override
    public ProviderDataResponse getProviderData(String providerString) {
        Provider provider = getProvider(providerString);
        LocalDate lastUpdate = resultService.getLastUpdateByProvider(provider);
        List<GameType> gameTypeData = resultService.getGameTypesDataByProvider(provider);
        gameTypeData.sort(Comparator.comparing(GameType::name));

        List<ProviderData> providersData = new ArrayList<>();

        for (GameType gameTypeDatum : gameTypeData) {
            List<StakeResponse> stakes = resultService.getStakesByByProviderAndGameType(provider, gameTypeDatum)
                    .stream()
                    .sorted(Comparator.comparing(Stake::getStakeEquivalent).reversed())
                    .map(stake -> new StakeResponse(stake.getCurrency(), stake.getStakeEquivalent().toString()))
                    .toList();
            providersData.add(new ProviderData(gameTypeDatum, stakes));
        }
        return new ProviderDataResponse(lastUpdate, providersData);
    }

    private Provider getProvider(String providerStr) {
        return Arrays.stream(Provider.values())
                .filter(provider -> provider.name().equals(providerStr))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Provider not found by name " + providerStr));
    }

}
