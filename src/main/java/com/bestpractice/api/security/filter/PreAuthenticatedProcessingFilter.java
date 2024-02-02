//package com.bestpractice.api.security.filter;
//
//import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
//import javax.servlet.http.HttpServletRequest;
//
//public class PreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
//
//    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
//        return "";
//    }
//
//    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
//        String credentials = httpServletRequest.getHeader("Authorization");
//        return credentials == null ? "" : credentials;
//    }
//}