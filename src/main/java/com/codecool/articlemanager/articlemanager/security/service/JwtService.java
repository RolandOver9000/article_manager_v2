package com.codecool.articlemanager.articlemanager.security.service;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtService {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliSeconds;

    private final String rolesFieldName = "roles";

    private final String userIdFieldName = "id";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<String> roles, String id) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(rolesFieldName, roles);
        claims.put(userIdFieldName, id);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliSeconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Optional<Cookie> getTokenCookieFromRequest(HttpServletRequest request) {
        if (request.getCookies() == null) return Optional.empty();
        return Arrays.stream(request.getCookies())
                .filter(cookie -> "JWT".equals(cookie.getName()))
                .findFirst();
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        return getTokenCookieFromRequest(request).map(Cookie::getValue).orElse(null);
    }

    public boolean validateToken(String token) {
        System.out.println("Validating token: " + token);
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return claims.getBody().getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            log.debug("JWT invalid " + e);
        }
        return false;
    }


    public Authentication parseUserFromTokenInfo(String token) throws UsernameNotFoundException {
        Claims body = parseTokenBody(token);
        String username = body.getSubject();
        List<String> roles = body.get(rolesFieldName, ArrayList.class);
        var authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toCollection(LinkedList::new));
        return new UsernamePasswordAuthenticationToken(username, "", authorities);
    }

    private Claims parseTokenBody(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
