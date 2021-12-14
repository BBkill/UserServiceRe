package aibless.userservicere.exception;

import org.springframework.http.HttpStatus;


public class UserAlreadyExited extends AbstractException{

    public UserAlreadyExited() {
        super("User Already exited", HttpStatus.BAD_REQUEST);
    }
}

