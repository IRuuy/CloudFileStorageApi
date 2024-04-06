package ru.shulgindaniil.cloudFileStorage.user.domain.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
