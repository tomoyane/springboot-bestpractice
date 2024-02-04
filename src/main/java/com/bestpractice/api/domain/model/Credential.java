package com.bestpractice.api.domain.model;

import java.util.Date;

public class Credential {
    private final String token;
    private final String tokenType;
    private final Date exp;
    private final boolean isRefresh;

    public Credential(String token, String tokenType, Date exp, boolean isRefresh) {
        this.token = token;
        this.tokenType = tokenType;
        this.exp = exp;
      this.isRefresh = isRefresh;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Date getExp() {
        return exp;
    }

    public boolean isRefresh() {
        return isRefresh;
    }
}
