package com.bestpractice.api.domain.filter;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import javax.servlet.http.HttpServletRequest;

public class PreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
        System.out.print("=====filter1====");
        return "";
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        System.out.print("=====filter2====");
        String credentials = httpServletRequest.getHeader("Authorization: Bearer");
        return credentials == null ? "" : credentials;
    }
}