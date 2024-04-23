package ru.shulgindaniil.cloudFileStorage.objectStorage.domain.exception;

public class FileOperationException extends RuntimeException {
    public FileOperationException() {
        super("There is an error while uploading/downloading the file, try again later");
    }
}
