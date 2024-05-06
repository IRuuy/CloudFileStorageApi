package ru.shulgindaniil.cloudFileStorage.common;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.shulgindaniil.cloudFileStorage.common.exception.BadRequestException;
import ru.shulgindaniil.cloudFileStorage.common.exception.InternalServerException;
import ru.shulgindaniil.cloudFileStorage.common.exception.ResourceForbiddenException;
import ru.shulgindaniil.cloudFileStorage.common.exception.ResourceNotFoundException;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.exception.FileObjectAlreadyExistException;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.exception.FileOperationException;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.exception.DirectoryNotEmptyException;
import ru.shulgindaniil.cloudFileStorage.security.PasswordNotEqualConfirmationPasswordException;
import ru.shulgindaniil.cloudFileStorage.user.domain.exception.UserAlreadyExistException;
import ru.shulgindaniil.cloudFileStorage.user.domain.exception.UserNotFoundException;

import java.util.Date;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(
            RuntimeException e,
            HttpServletRequest request
    ) {
        return createResponse(e, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenException(
            RuntimeException e,
            HttpServletRequest request
    ) {
        return createResponse(e, request, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({
            BadRequestException.class,
            AuthenticationException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestException(
            RuntimeException e,
            HttpServletRequest request
    ) {
        return createResponse(e, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorResponse> handleException(
            RuntimeException e,
            HttpServletRequest request
    ) {
        return createResponse(e, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> createResponse(
            RuntimeException e,
            HttpServletRequest request,
            HttpStatus status
    ) {
        ErrorResponse apiError = new ErrorResponse(
                request.getRequestURI(),
                e.getMessage(),
                status.value(),
                new Date()
        );

        return new ResponseEntity<>(apiError, status);
    }
}
