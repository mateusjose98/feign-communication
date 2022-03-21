package com.mateus.online.store.service.external.config;

import feign.Param;

import java.time.OffsetDateTime;

public class OffsetDateTimeToMillisExpander implements Param.Expander{

    @Override
    public String expand(Object value) {

        if (!OffsetDateTime.class.isAssignableFrom(value.getClass())){
            throw new IllegalArgumentException("Bad expander");
        }
        long millis = ((OffsetDateTime) value).toInstant().toEpochMilli();

        return "" + millis;
    }
}
