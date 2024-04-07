package ru.shulgindaniil.cloudFileStorage.security.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.shulgindaniil.cloudFileStorage.security.web.dto.LoginRequest;
import ru.shulgindaniil.cloudFileStorage.user.service.UserService;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserDto;
import ru.shulgindaniil.cloudFileStorage.user.web.validation.OnCreate;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;


@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public static final String SIGN_IN = "/api/v1/signin";
    public static final String SIGN_UP = "/api/v1/signup";
    public static final String CSRF = "/api/v1/csrf";

    @PostMapping(SIGN_IN)
    public ResponseEntity<?> signIn(HttpServletRequest req,
                                    @Validated @RequestBody LoginRequest loginRequest) {
        Authentication authRequest = UsernamePasswordAuthenticationToken.unauthenticated(
                        loginRequest.getEmail(), loginRequest.getPassword()
        );
        Authentication authResponse = authenticationManager.authenticate(authRequest);

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authResponse);
        HttpSession session = req.getSession();
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, context);

        return ResponseEntity.ok(authResponse.getPrincipal());
    }

    @PostMapping(SIGN_UP)
    public ResponseEntity<UserDto> signUp(@Validated(OnCreate.class) @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.create(dto));
    }

    @GetMapping(CSRF)
    public void getCsrfToken(HttpServletResponse response, CsrfToken csrfToken) {
        response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
    }
}
