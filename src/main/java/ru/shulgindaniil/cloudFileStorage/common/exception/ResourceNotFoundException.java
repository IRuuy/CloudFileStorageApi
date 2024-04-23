package ru.shulgindaniil.cloudFileStorage.common.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource not found.");
    }
    public ResourceNotFoundException(String id) {
        super(String.format("Resource with id - %s not found.", id));
    }
}
