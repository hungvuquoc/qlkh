package com.example.qlkh.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.qlkh.constant.Variables;
import com.example.qlkh.dto.EbsPrincipal;
import com.example.qlkh.entity.User;
import com.example.qlkh.utils.EbsConvertUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final JwtTokenBlacklist tokenBlacklist;

    public CustomAuthorizationFilter(JwtTokenBlacklist tokenBlacklist) {
        this.tokenBlacklist = tokenBlacklist;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring("Bearer ".length());
        if (tokenBlacklist.isBlacklisted(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        processJwtAuthentication(token);
        filterChain.doFilter(request, response);
    }

    private void processJwtAuthentication(String token) {
        Algorithm algorithm = Algorithm.HMAC256(Variables.SECRET_KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

//        String username = decodedJWT.getSubject();
//        Long userId = decodedJWT.getClaim("id").asLong();
//        User user = new User();
//        user.setId(userId);
//        user.setUsername(username);
        String jsonUser = decodedJWT.getClaim("user").asString();
        EbsPrincipal principal = EbsConvertUtils.deserializeJson(jsonUser, EbsPrincipal.class);

        String[] authorityNames = decodedJWT.getClaim("authorities").asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Arrays.stream(authorityNames).forEach((authorityName) -> authorities.add(new SimpleGrantedAuthority(authorityName)));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
