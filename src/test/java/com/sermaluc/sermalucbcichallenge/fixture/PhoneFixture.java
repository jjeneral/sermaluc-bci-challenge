package com.sermaluc.sermalucbcichallenge.fixture;

import com.sermaluc.sermalucbcichallenge.model.dto.PhoneDto;
import com.sermaluc.sermalucbcichallenge.model.entity.Phone;

public class PhoneFixture {
    public static PhoneDto getPhoneDto() {
        return PhoneDto.builder()
                .number(1234567)
                .citycode((byte) 1)
                .countrycode((short) 57)
                .build();
    }

    public static Phone getPhone() {
        return Phone.builder()
                .number(1234567)
                .citycode((byte) 1)
                .countrycode((short) 57)
                .build();
    }
}
