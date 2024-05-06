package ru.shulgindaniil.cloudFileStorage.objectStorage.domain.exception;

import ru.shulgindaniil.cloudFileStorage.common.exception.InternalServerException;

public class FileOperationException extends InternalServerException {
    public FileOperationException() {
        super("There is an error while uploading/downloading the file, try again later");
    }
}
