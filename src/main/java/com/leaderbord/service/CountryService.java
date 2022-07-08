package com.leaderbord.service;

import com.leaderbord.entity.Country;

public interface CountryService {
    Country getByCode(String code);
}
