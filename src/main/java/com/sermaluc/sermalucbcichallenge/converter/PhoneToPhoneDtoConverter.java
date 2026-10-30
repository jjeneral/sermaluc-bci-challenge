package com.sermaluc.sermalucbcichallenge.converter;

import com.sermaluc.sermalucbcichallenge.model.dto.PhoneDto;
import com.sermaluc.sermalucbcichallenge.model.entity.Phone;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PhoneToPhoneDtoConverter implements Converter<Phone, PhoneDto> {
    @Override
    public PhoneDto convert(Phone source) {
        return PhoneDto.builder()
                .number(source.getNumber())
                .citycode(source.getCitycode())
                .countrycode(source.getCountrycode())
                .build();
    }
}
