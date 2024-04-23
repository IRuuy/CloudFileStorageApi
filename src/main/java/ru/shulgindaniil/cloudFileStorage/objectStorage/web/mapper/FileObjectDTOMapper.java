package ru.shulgindaniil.cloudFileStorage.objectStorage.web.mapper;

import org.mapstruct.Mapper;
import ru.shulgindaniil.cloudFileStorage.common.Mappable;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectDTO;

import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObject;

@Mapper(componentModel = "spring", uses = {FileObjectBaseDTOMapper.class})
public interface FileObjectDTOMapper extends Mappable<FileObject, FileObjectDTO> {
}
