package ru.shulgindaniil.cloudFileStorage.objectStorage.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Qualifier;
import ru.shulgindaniil.cloudFileStorage.common.Mappable;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObject;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectBaseDTO;

@Mapper(componentModel = "spring")
public interface FileObjectBaseDTOMapper extends Mappable<FileObject, FileObjectBaseDTO> { }
