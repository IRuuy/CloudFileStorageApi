package ru.shulgindaniil.cloudFileStorage.objectStorage.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.shulgindaniil.cloudFileStorage.common.Mappable;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObject;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectMiniDTO;

@Mapper(
        componentModel = "spring",
        uses = {FileObjectBaseDTOMapper.class}
)
public interface FileObjectMiniDTOMapper extends Mappable<FileObject, FileObjectMiniDTO> {
    @Mapping(target = "parentId", source = "parent.id")
    FileObjectMiniDTO toTarget(FileObject source);

    @Mapping(target = "parent.id", source = "parentId")
    FileObject fromTarget(FileObjectMiniDTO target);
}
