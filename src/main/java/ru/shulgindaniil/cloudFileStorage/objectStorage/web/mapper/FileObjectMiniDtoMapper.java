package ru.shulgindaniil.cloudFileStorage.objectStorage.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.shulgindaniil.cloudFileStorage.common.Mappable;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObject;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectMiniDto;
import ru.shulgindaniil.cloudFileStorage.user.web.mapper.UserMiniDtoMapper;

@Mapper(
        componentModel = "spring",
        uses = {FileObjectBaseDtoMapper.class, UserMiniDtoMapper.class}
)
public interface FileObjectMiniDtoMapper extends Mappable<FileObject, FileObjectMiniDto> {
    @Mapping(target = "parentId", source = "parent.id")
    FileObjectMiniDto toDto(FileObject entity);

    @Mapping(target = "parent.id", source = "parentId")
    FileObject toEntity(FileObjectMiniDto dto);
}
