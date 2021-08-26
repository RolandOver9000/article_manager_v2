package com.codecool.articlemanager.articlemanager.service.cookie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class CookieService {

    public static final String TOKEN_COOKIE_NAME = "JWT";

    public ResponseCookie createTokenCookie(String jwt, Duration maxAge) {
        return ResponseCookie.from(TOKEN_COOKIE_NAME, jwt)
                .sameSite("Strict")
                .httpOnly(true)
                .path("/")
                .maxAge(maxAge)
                .build();
    }

    public void invalidateTokenCookie(HttpServletResponse response) {
        log.info("Invalidating JWT cookie.");
        ResponseCookie cookie = createTokenCookie("", Duration.ZERO);
        response.addHeader("Set-Cookie", cookie.toString());
    }

}
