package ru.shulgindaniil.cloudFileStorage.objectStorage.web.mapper;

import org.mapstruct.Mapper;
import ru.shulgindaniil.cloudFileStorage.common.Mappable;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObjectType;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectTypeDto;

@Mapper(componentModel = "spring")
public interface FileObjectTypeDtoMapper extends Mappable<FileObjectType, FileObjectTypeDto> { }
