package ru.shulgindaniil.cloudFileStorage.common.exception;

public class ResourceForbiddenException extends RuntimeException {
    public ResourceForbiddenException() {
        super("You haven`t authorities for get this resource.");
    }
}
