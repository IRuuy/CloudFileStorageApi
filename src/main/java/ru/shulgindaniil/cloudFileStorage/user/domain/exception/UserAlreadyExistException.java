package ru.shulgindaniil.cloudFileStorage.user.domain.exception;

import ru.shulgindaniil.cloudFileStorage.common.exception.BadRequestException;

public class UserAlreadyExistException extends BadRequestException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
