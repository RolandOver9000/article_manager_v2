package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.service.cookie.CookieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogoutService {

    private final CookieService cookieService;

    public void logout(HttpServletResponse httpResponse) {
        ResponseCookie invalidResponseCookie = cookieService.createInvalidTokenCookie();
        httpResponse.addHeader("Set-Cookie", invalidResponseCookie.toString());
    }
}
