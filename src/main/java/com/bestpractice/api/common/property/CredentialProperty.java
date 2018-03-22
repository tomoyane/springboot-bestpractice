package com.bestpractice.api.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "settings")
public class AppProperty {

    private String provider;
    private String subject;

    public String getProvider() {
        return provider;
    }

    public String getSubject() {
        return subject;
    }
}