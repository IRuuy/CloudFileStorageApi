package ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserMiniDTO;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
public class FileObjectDTO extends FileObjectBaseDTO {
    private UserMiniDTO owner;
    private FileObjectMiniDTO parent;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private Long size;
}
