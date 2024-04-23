package ru.shulgindaniil.cloudFileStorage.objectStorage.domain.exception;

public class DirectoryNotEmptyException extends RuntimeException {
    public DirectoryNotEmptyException(String folderName) {
        super(String.format("The directory - %s is not empty!", folderName));
    }

    public DirectoryNotEmptyException() {
        super("The directory is not empty!");
    }
}
