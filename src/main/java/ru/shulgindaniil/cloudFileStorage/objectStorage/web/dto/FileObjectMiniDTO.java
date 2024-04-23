package ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObjectType;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class FileObjectMiniDTO extends FileObjectBaseDTO {
    private String parentId;

    public FileObjectMiniDTO(String name, FileObjectType type, String parentId) {
        super(name, type);
        this.parentId = parentId;
    }
}
