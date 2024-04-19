package ru.shulgindaniil.cloudFileStorage.objectStorage.web.mapper;

import org.mapstruct.Mapper;
import ru.shulgindaniil.cloudFileStorage.common.Mappable;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectDto;

import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObject;

@Mapper(componentModel = "spring", uses = {FileObjectBaseDtoMapper.class})
public interface FileObjectDtoMapper extends Mappable<FileObject, FileObjectDto> {

}
