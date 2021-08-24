package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.model.dto.LoginDTO;
import com.codecool.articlemanager.articlemanager.model.entity.UserEntity;
import com.codecool.articlemanager.articlemanager.repository.UserRepository;
import com.codecool.articlemanager.articlemanager.security.service.JwtService;
import com.codecool.articlemanager.articlemanager.service.cookie.CookieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final CookieService cookieService;

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

    private ResponseCookie generateResponseCookie(UserEntity userEntity, List<String> roles) {
        String token = jwtService.createToken(userEntity.getUsername(), roles, Long.toString(userEntity.getId()));
        return cookieService.createTokenCookie(token, Duration.ofHours(24));
    }

    private Long login(Authentication authentication, HttpServletResponse response) {
        List<String> roles = getRolesFrom(authentication);
        String username = authentication.getName();
        //try to collect only the id of the user
        UserEntity foundUser = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        ResponseCookie generatedCookie = generateResponseCookie(foundUser, roles);
        response.addHeader("Set-Cookie", generatedCookie.toString());
        return foundUser.getId();
    }

    private List<String> getRolesFrom(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

}
