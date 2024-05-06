package ru.shulgindaniil.cloudFileStorage.user.domain.exception;

import ru.shulgindaniil.cloudFileStorage.common.exception.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
