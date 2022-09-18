package com.leaderboard.service.interfaces;

import com.leaderboard.entity.Country;

public interface CountryService {

    Country getByCode(String code);

}
