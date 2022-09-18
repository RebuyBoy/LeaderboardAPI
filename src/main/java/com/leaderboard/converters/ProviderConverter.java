package com.leaderboard.converters;

import com.leaderboard.entity.Provider;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
@Component
public class ProviderConverter implements AttributeConverter<Provider, String> {

    @Override
    public String convertToDatabaseColumn(Provider provider) {
        return provider == null ? null : provider.getCode();
    }

    @Override
    public Provider convertToEntityAttribute(String code) {
        return Stream.of(Provider.values())
                .filter(provider -> provider.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found provider by code: " + code));
    }

    public Provider convertToEntityAttributeByName(String providerStr) {
        return Stream.of(Provider.values())
                .filter(provider -> provider.name().equals(providerStr))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found providerStr by code: " + providerStr));
    }

}

