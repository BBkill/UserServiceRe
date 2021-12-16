package aibless.userservicere.exception;

import org.springframework.http.HttpStatus;

public class UserPhoneNumberAlreadyExited extends AbstractException{

    public UserPhoneNumberAlreadyExited() {
        super("phone number exited", HttpStatus.BAD_REQUEST);
    }
}
