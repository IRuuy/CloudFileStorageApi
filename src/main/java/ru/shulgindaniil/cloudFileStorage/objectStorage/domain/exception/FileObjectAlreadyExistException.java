package ru.shulgindaniil.cloudFileStorage.objectStorage.domain.exception;

import ru.shulgindaniil.cloudFileStorage.common.exception.BadRequestException;

public class FileObjectAlreadyExistException extends BadRequestException {
    public FileObjectAlreadyExistException(String name) {
        super(String.format("File object with name - %s is already exists!", name));
    }
}
