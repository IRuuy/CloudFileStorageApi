package ru.shulgindaniil.cloudFileStorage.objectStorage.service;

import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObjectType;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectBaseDTO;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectDTO;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectMiniDTO;

import java.util.Collection;

public interface FileObjectService {
    FileObjectDTO createFileObject(FileObjectMiniDTO fileObjectMiniDto, String ownerId, Long size);
    FileObjectDTO getFileObject(String fileObjectId, String ownerId, FileObjectType type);
    void deleteFileObject(String folderId, String ownerId, FileObjectType type, boolean isRecursed);

    FileObjectDTO getBaseParent(String ownerId);
    FileObjectDTO createBaseParent(String ownerId);

    Collection<FileObjectBaseDTO> getAllParents(String fileObjectId);
    Collection<FileObjectDTO> getAllChildren(String fileObjectId, String ownerId);
}
