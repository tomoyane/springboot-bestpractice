package com.bestpractice.api.domain.service;

import com.bestpractice.api.domain.entity.User;
import com.bestpractice.api.domain.model.CredentialModel;
import com.bestpractice.api.security.role.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class AuthenticationService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final UserService userService;
    private final JsonWebTokenService jsonWebTokenService;

    public AuthenticationService(UserService userService, JsonWebTokenService jsonWebTokenService) {
        this.userService = userService;
        this.jsonWebTokenService = jsonWebTokenService;
    }

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        Object credentials = token.getCredentials();

        if (credentials.toString().equals("")) {
            throw new BadCredentialsException("Bad credential");
        }

        if (!this.jsonWebTokenService.verifyJwt(credentials.toString())) {
            throw new BadCredentialsException("Bad credential");
        }

        CredentialModel credentialModel = this.jsonWebTokenService.decodeJwt(credentials.toString());
        if (credentialModel.getSub() == null || credentialModel.getJti() == null) {
            throw new BadCredentialsException("Bad credential");
        }

        Long userId = credentialModel.getSub();
        String userUuid = credentialModel.getJti();

        User user = this.userService.getUserByIdAndUserUuid(userId, userUuid);
        if (user == null) {
            throw new UsernameNotFoundException("Not found user");
        }

        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>() ;
        authorities.add(new UserAuthority());

        return new org.springframework.security.core.userdetails.User(userUuid,"", authorities);
    }
}
