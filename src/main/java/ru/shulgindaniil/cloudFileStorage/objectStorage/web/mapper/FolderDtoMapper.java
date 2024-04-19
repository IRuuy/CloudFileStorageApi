package ru.shulgindaniil.cloudFileStorage.objectStorage.web.mapper;

import org.mapstruct.Mapper;
import ru.shulgindaniil.cloudFileStorage.common.Mappable;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObject;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FolderDto;

@Mapper(componentModel = "spring", uses = {FileObjectDtoMapper.class})
public interface FolderDtoMapper extends Mappable<FileObject, FolderDto> { }
