package aibless.userservicere.security.config;

import aibless.userservicere.security.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationFilter jwtFilter;

    @Override
    public void configure(HttpSecurity builder) {
        builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
