package ru.shulgindaniil.cloudFileStorage.objectStorage.domain.exception;

public class FileObjectAlreadyExistException extends RuntimeException {
    public FileObjectAlreadyExistException(String name) {
        super(String.format("File object with name - %s is already exists!", name));
    }
}
