package aibless.userservicere.security;

//import aibless.userservicere.security.service.AuthenticationUserDetailService;
import aibless.userservicere.exception.JwtAuthenticationException;
import aibless.userservicere.security.service.JwtProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
@Log4j2
public class AuthenticationFilter extends OncePerRequestFilter {
//    private final AuthenticationUserDetailService authenticationUserDetailsService;
//
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtProvider.getToken(request);
        if (token != null && jwtProvider.validateToken(token)) {
            Authentication authentication = jwtProvider.getAuthentication(token);
            if (authentication != null){
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else {
            log.info("doFilter");
            throw new JwtAuthenticationException();
        }


        filterChain.doFilter(request, response);
    }
}