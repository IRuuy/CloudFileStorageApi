package ru.shulgindaniil.cloudFileStorage.security.exception;

public class PasswordNotEqualConfirmationPasswordException extends RuntimeException {
    public PasswordNotEqualConfirmationPasswordException() {
        super("Password not equals confirmation password!");
    }
}
