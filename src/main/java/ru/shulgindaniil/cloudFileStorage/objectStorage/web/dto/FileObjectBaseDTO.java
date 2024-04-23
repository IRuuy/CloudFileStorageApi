package ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObjectType;

@Data
@NoArgsConstructor
public class FileObjectBaseDTO {
    private String id;
    private String name;
    private FileObjectType type;

    public FileObjectBaseDTO(String name, FileObjectType type) {
        this.name = name;
        this.type = type;
    }
}
