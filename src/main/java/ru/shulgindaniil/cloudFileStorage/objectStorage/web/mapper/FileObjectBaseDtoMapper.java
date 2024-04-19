package ru.shulgindaniil.cloudFileStorage.objectStorage.web.mapper;

import org.mapstruct.Mapper;
import ru.shulgindaniil.cloudFileStorage.common.Mappable;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObject;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectBaseDto;

@Mapper(componentModel = "spring", uses = {FileObjectTypeDtoMapper.class})
public interface FileObjectBaseDtoMapper extends Mappable<FileObject, FileObjectBaseDto> { }
