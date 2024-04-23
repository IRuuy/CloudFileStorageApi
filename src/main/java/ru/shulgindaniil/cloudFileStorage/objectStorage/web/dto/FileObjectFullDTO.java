package ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
public class FileObjectFullDTO extends FileObjectDTO {
    private Collection<FileObjectBaseDTO> path = new ArrayList<>();
}