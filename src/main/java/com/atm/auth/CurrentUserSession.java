package com.atm.auth;

import com.atm.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;


// To save session related data
@Getter
@Setter
@Component
@RequestScope
public class CurrentUserSession {
    private User user;
}

