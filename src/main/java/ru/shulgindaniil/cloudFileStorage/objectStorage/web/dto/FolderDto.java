package ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
public class FolderDto extends FileObjectDto {
    private Collection<FileObjectBaseDto> path;
}