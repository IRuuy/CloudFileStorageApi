package ru.shulgindaniil.cloudFileStorage.security;

import ru.shulgindaniil.cloudFileStorage.common.exception.BadRequestException;

public class PasswordNotEqualConfirmationPasswordException extends BadRequestException {
    public PasswordNotEqualConfirmationPasswordException() {
        super("Password not equals confirmation password!");
    }
}
