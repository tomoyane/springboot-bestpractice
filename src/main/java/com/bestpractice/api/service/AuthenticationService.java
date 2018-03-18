package com.bestpractice.api.service;

import com.bestpractice.api.domain.repository.UserRepository;
import com.bestpractice.api.domain.role.AdminAuthority;
import com.bestpractice.api.domain.role.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
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
    UserRepository userRepository;
    @Autowired
    JsonWebTokenService jsonWebTokenService;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        Object credentials = token.getCredentials();

        if(credentials.toString().equals("")) {
            throw new UsernameNotFoundException("Not found user");
        }

        Long userId = jsonWebTokenService.decodeJwt(credentials.toString());
        if (userRepository.findById(userId) == null) {
            throw new RuntimeException();
        }

        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>() ;
        authorities.add(new UserAuthority());
        authorities.add(new AdminAuthority());

        return new User("","", authorities);
    }
}
