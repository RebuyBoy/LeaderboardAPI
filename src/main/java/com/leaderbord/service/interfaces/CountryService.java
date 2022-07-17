package com.leaderbord.service.interfaces;

import com.leaderbord.entity.Country;

public interface CountryService {
    Country getByCode(String code);
}
