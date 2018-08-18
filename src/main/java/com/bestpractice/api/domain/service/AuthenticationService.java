package com.bestpractice.api.domain.service;

import com.bestpractice.api.domain.entity.User;
import com.bestpractice.api.domain.model.Credential;
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

    @Autowired
    UserService userService;
    @Autowired
    JsonWebTokenService jsonWebTokenService;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        Object credentials = token.getCredentials();

        if (credentials.toString().equals("")) {
            throw new BadCredentialsException("Bad credential");
        }

        if (!this.jsonWebTokenService.verifyJwt(credentials.toString())) {
            throw new BadCredentialsException("Bad credential");
        }

        Credential credential = this.jsonWebTokenService.decodeJwt(credentials.toString());
        if (credential.getSub() == null || credential.getJti() == null) {
            throw new BadCredentialsException("Bad credential");
        }

        Long userId = credential.getSub();
        String userUuid = credential.getJti();

        User user = this.userService.getUserByIdAndUserUuid(userId, userUuid);
        if (user == null) {
            throw new UsernameNotFoundException("Not found user");
        }

        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>() ;
        authorities.add(new UserAuthority());

        return new org.springframework.security.core.userdetails.User(userUuid,"", authorities);
    }
}
