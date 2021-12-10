package aibless.userservicere.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(AbstractException.class)
    public ResponseEntity <String> handleException(AbstractException e)
    {
        return new ResponseEntity<>(e.getMessage(),e.getStatus());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidation (MethodArgumentNotValidException e)
    {
        Map<String, String> map = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName = ((FieldError) err).getField();
            String errorMessage = err.getDefaultMessage();
            map.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }


}