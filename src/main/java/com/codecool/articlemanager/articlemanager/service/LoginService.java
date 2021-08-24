package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.model.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    public Long tryLogin(LoginDTO loginDTO, HttpServletResponse response) {
        Authentication authentication = tryAuthenticate(loginDTO);
        if (authentication == null)
            return -1L;
        return login(authentication, response);
    }

    private void logTryAuthentication(LoginDTO loginDTO) {
        log.info("Try to authenticate with login username: " +
                loginDTO.getUsername() + " login password: " +
                loginDTO.getPassword());
    }

    private Authentication tryAuthenticate(LoginDTO loginDTO) {
        logTryAuthentication(loginDTO);
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
    }

    

}
