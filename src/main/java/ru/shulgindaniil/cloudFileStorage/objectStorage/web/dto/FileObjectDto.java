package ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.shulgindaniil.cloudFileStorage.user.web.dto.UserMiniDto;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
public class FileObjectDto extends FileObjectBaseDto {
    private UserMiniDto owner;
    private FileObjectMiniDto parent;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private Integer size;
}
