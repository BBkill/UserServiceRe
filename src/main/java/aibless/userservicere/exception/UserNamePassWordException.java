package aibless.userservicere.exception;

import org.springframework.http.HttpStatus;

public class UserNamePassWordException extends AbstractException{
    public UserNamePassWordException() {
        super("user name or password invalid", HttpStatus.UNAUTHORIZED);
    }
}
