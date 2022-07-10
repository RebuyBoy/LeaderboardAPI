package com.leaderbord.service;

import com.leaderbord.entity.Country;
import com.leaderbord.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country getByCode(String code) {
        return countryRepository.getById(code);
    }
}
