package com.sermaluc.sermalucbcichallenge.converter;

import com.sermaluc.sermalucbcichallenge.model.dto.PhoneDto;
import com.sermaluc.sermalucbcichallenge.model.entity.Phone;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PhoneDtoToPhoneConverter implements Converter<PhoneDto, Phone> {
    @Override
    public Phone convert(PhoneDto source) {
        return Phone.builder()
                .number(source.getNumber())
                .citycode(source.getCitycode())
                .countrycode(source.getCountrycode())
                .build();
    }
}
