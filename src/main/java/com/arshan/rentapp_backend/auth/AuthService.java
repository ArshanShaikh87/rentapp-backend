package com.arshan.rentapp_backend.auth;

import com.arshan.rentapp_backend.auth.dto.LoginRequest;
import com.arshan.rentapp_backend.auth.dto.LoginResponse;
import com.arshan.rentapp_backend.security.RentAppUserDetails;
import com.arshan.rentapp_backend.user.RentAppUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    public AuthService(AuthenticationManager authenticationManager,
                       SecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    public LoginResponse login(LoginRequest loginRequest,
                               HttpServletRequest request,
                               HttpServletResponse response) {

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.loginId(),
                        loginRequest.password());

        SecurityContextHolder.clearContext();

        Authentication authenticated =
                authenticationManager.authenticate(authentication);

        RentAppUserDetails userDetails =
                (RentAppUserDetails) authenticated.getPrincipal();
        RentAppUser user = userDetails.getUser();
        LoginResponse loginResponse = new LoginResponse(
                user.getId(),user.getLoginId(),user.getRole());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticated);
        SecurityContextHolder.setContext(context);

        securityContextRepository.saveContext(context, request, response);

        return loginResponse;
    }

    public LoginResponse getCurrentUser(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null ||  !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken)
        {
            throw new AuthenticationCredentialsNotFoundException("Anonymous User");
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof RentAppUserDetails userDetails)) {
            throw new InsufficientAuthenticationException("Invalid user principal");
        }

        RentAppUser user = userDetails.getUser();
        return new LoginResponse(
                user.getId(), user.getLoginId(), user.getRole());

    }
}
