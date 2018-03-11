package com.bestpractice.api.domain.role;

import org.springframework.security.core.GrantedAuthority;

public class UserAuthority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "ROLE_USER";
    }
}
