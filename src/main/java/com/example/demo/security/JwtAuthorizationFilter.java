package com.example.demo.security;

import com.example.demo.util.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Value("${security.jwt.secretKey}")
    private String jwtSecret;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String requestURL = request.getRequestURI();
        if (checkURLRequest(requestURL)) {
            chain.doFilter(request, response);
            return;
        }

        String token = getJwtFromRequest(request);
        if (token != null && !tokenBlacklistService.isBlacklisted(token)) {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, new ArrayList<>());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token has been blacklisted or is invalid.");
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean checkURLRequest(String request) {
        return Arrays.asList(Constants.EXCLUDED_PATHS).contains(request);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constants.TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(Constants.TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}