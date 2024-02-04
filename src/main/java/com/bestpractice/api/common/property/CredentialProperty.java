package com.bestpractice.api.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "credentials")
public class CredentialProperty {
    private String key;
    private String provider;
    private String subject;
    private String alg;
    private String hmacSecret;
    private String expiresHourStr;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getHmacSecret() {
        return hmacSecret;
    }

    public void setHmacSecret(String hmacSecret) {
        this.hmacSecret = hmacSecret;
    }

    public String getExpiresHourStr() {
        return expiresHourStr;
    }

    public void setExpiresHourStr(String expiresHourStr) {
        this.expiresHourStr = expiresHourStr;
    }

    public Integer convertToIntExpires() {
        if (this.expiresHourStr.equals("-")) {
            return null;
        }

        try{
            return Integer.parseInt(this.expiresHourStr);
        }
        catch (NumberFormatException ignore) {
            return null;
        }
    }
}