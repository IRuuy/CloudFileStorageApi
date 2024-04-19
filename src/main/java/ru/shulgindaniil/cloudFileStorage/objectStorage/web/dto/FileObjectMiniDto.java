package ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FileObjectMiniDto extends FileObjectBaseDto {
    private String parentId;
}
