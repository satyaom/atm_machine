package com.atm.config;

import com.atm.auth.CustomAuthProvider;
import com.atm.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthProvider authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    // Here we can set on which route we want authentication also gradual level control given csrf disable
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic() // For Basic authentication, using username and password
                .and()
                .authorizeRequests()
                .antMatchers("/users/login", "/users/signup") // ignore these routes from authentication
                .permitAll()
                .antMatchers("/admin/**").hasRole(UserRole.ADMIN.toString()) // Only ADMIN role for /admin paths
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}


