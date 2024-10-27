package com.atm.auth;

import com.atm.enums.UserRole;
import com.atm.model.User;
import com.atm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    private final CurrentUserSession currentUserSession;

    private final UserService userService;

    @Autowired
    public CustomAuthProvider(CurrentUserSession currentUserSession, UserService userService) {
        this.currentUserSession = currentUserSession;
        this.userService = userService;
    }

    // Used by SecurityConfig, internally authenticate called before serving the route
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();
        String userId = authentication.getPrincipal().toString();
        // getUserByToken going to fetch token from redis and verify user existence also
        User user = userService.getUserByToken(token, userId);
        currentUserSession.setUser(user);
        // GrantedAuthority give us ability to set permission api level based on authority is ADMIN or CUSTOMER
        List<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority a = new SimpleGrantedAuthority(user.getRole().toString());
        authorities.add(a);
        return new UsernamePasswordAuthenticationToken(user.getId(), token, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
