package com.be.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends GenericFilterBean {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final JWTTokenProvider jwtTokenProvider;
    private final CustomUserDetailService customUserDetailService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String jwtToken = parseToken((HttpServletRequest) servletRequest);

        if (jwtToken != null && jwtTokenProvider.validateToken(jwtToken)) {
            Claims claims = jwtTokenProvider.parseClaims(jwtToken);
            UserDetails userDetails = customUserDetailService.loadUserByUsername(claims.get("email").toString());

            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String parseToken(HttpServletRequest servletRequest) {
        String jwtToken = servletRequest.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(jwtToken) && jwtToken.startsWith("Bearer ")) {
            return jwtToken.substring(7);
        }
        return null;
    }
}
