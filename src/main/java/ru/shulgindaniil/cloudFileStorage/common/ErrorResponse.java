package ru.shulgindaniil.cloudFileStorage.common;

import java.util.Date;

public record ErrorResponse (
    String path,
    String message,
    int statusCode,
    Date timestamp
) {}