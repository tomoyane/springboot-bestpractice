package com.bestpractice.api.service;

import com.bestpractice.api.domain.repository.UserKeyRepository;
import com.bestpractice.api.domain.role.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class AuthenticationService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Autowired
    UserKeyRepository userKeyRepository;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) {

        Object credentials = token.getCredentials();

        if(credentials.toString().equals("")) {
            throw new RuntimeException();
        }

        if (userKeyRepository.findByToken(credentials.toString()) == null) {
            throw new RuntimeException();
        }

        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>() ;
        authorities.add(new UserAuthority());
//        authorities.add(new AdminAuthority());

        //org.springframework.security.core.userdetails.Userを利用。
        //UserNameは都度適切に設定し,Passwordなどはないので常にブランクにした。
        return new User("","", authorities);
    }
}
