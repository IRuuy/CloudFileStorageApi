package ru.shulgindaniil.cloudFileStorage.objectStorage.domain.exception;

import ru.shulgindaniil.cloudFileStorage.common.exception.BadRequestException;

public class DirectoryNotEmptyException extends BadRequestException {
    public DirectoryNotEmptyException(String folderName) {
        super(String.format("The directory - %s is not empty!", folderName));
    }

    public DirectoryNotEmptyException() {
        super("The directory is not empty!");
    }
}
