package ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto;

import lombok.Data;

@Data
public class FileObjectBaseDto {
    private String id;
    private String name;
    private FileObjectTypeDto type;
}
