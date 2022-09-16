package com.leaderboard.service;

import com.leaderboard.dto.api.response.ProviderData;
import com.leaderboard.dto.api.response.ProviderDataResponse;
import com.leaderboard.dto.api.response.ProviderResponse;
import com.leaderboard.dto.api.response.StakeResponse;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Stake;
import com.leaderboard.exceptions.ProviderNotFoundException;
import com.leaderboard.service.interfaces.ProviderService;
import com.leaderboard.service.interfaces.ResultService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        List<ProviderData> providersData = getProviderData(provider);

        return new ProviderDataResponse(lastUpdate, providersData);
    }

    private List<ProviderData> getProviderData(Provider provider) {

        List<GameType> gameTypeData = resultService.getGameTypesDataByProvider(provider);

        return gameTypeData.stream()
                .map(gameTypeDatum -> new ProviderData(gameTypeDatum, getStakeResponses(provider, gameTypeDatum)))
                .toList();
    }

    private List<StakeResponse> getStakeResponses(Provider provider, GameType gameTypeDatum) {
        return resultService.getStakesByByProviderAndGameType(provider, gameTypeDatum)
                .stream()
                .sorted(Comparator.comparing(Stake::getStakeEquivalent).reversed())
                .map(stake -> new StakeResponse(stake.getCurrency(), stake.getStakeEquivalent().toString()))
                .toList();
    }

    private Provider getProvider(String provider) {
        return Arrays.stream(Provider.values())
                .filter(value -> value.name().equals(provider))
                .findFirst()
                .orElseThrow(() -> new ProviderNotFoundException("Provider not found by name " + provider));
    }

}
