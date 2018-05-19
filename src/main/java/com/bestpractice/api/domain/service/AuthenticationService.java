package com.bestpractice.api.domain.service;

import com.bestpractice.api.domain.entity.UserEntity;
import com.bestpractice.api.domain.model.CredentialModel;
import com.bestpractice.api.security.role.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
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

        if (!jsonWebTokenService.verifyJwt(credentials.toString())) {
            throw new BadCredentialsException("Bad credential");
        }

        CredentialModel credentialModel = jsonWebTokenService.decodeJwt(credentials.toString());
        if (credentialModel.getSub() == null || credentialModel.getJti() == null) {
            throw new BadCredentialsException("Bad credential");
        }

        Long userId = credentialModel.getSub();
        String userUuid = credentialModel.getJti();

        UserEntity userEntity = userService.getUserByIdAndUserUuid(userId, userUuid);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Not found user");
        }

        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>() ;
        authorities.add(new UserAuthority());

        return new User(userUuid,"", authorities);
    }
}
