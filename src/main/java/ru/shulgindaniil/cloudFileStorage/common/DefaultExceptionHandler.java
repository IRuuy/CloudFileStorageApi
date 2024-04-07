package ru.shulgindaniil.cloudFileStorage.common;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.shulgindaniil.cloudFileStorage.security.exception.PasswordNotEqualConfirmationPasswordException;
import ru.shulgindaniil.cloudFileStorage.user.domain.exception.UserAlreadyExistException;
import ru.shulgindaniil.cloudFileStorage.user.domain.exception.UserNotFoundException;

import java.util.Date;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException e,
                                                                 HttpServletRequest request) {
        ErrorResponse apiError = new ErrorResponse(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date()
        );

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            UserAlreadyExistException.class,
            AuthenticationException.class,
            PasswordNotEqualConfirmationPasswordException.class,
    })
    public ResponseEntity<ErrorResponse> handleBadRequestException(RuntimeException e,
                                                                   HttpServletRequest request) {
        ErrorResponse apiError = new ErrorResponse(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                new Date()
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
