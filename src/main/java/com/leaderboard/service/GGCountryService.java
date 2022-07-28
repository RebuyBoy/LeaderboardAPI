package com.leaderboard.service;

import com.leaderboard.entity.Country;
import com.leaderboard.repository.CountryRepository;
import com.leaderboard.service.interfaces.CountryService;
import org.springframework.stereotype.Service;

@Service
public class GGCountryService implements CountryService {
    private final CountryRepository countryRepository;

    public GGCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country getByCode(String code) {
        return countryRepository.getByCode(code);
    }
}
