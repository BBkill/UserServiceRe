package aibless.userservicere.exception;

import org.springframework.http.HttpStatus;

public class JwtAuthenticationException extends AbstractException{
    public JwtAuthenticationException() {
        super("JWT token is expired or invalid", HttpStatus.UNAUTHORIZED);
    }
}
