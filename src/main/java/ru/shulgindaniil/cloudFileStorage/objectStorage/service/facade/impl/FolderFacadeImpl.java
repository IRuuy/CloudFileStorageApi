package ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import ru.shulgindaniil.cloudFileStorage.common.exception.ResourceNotFoundException;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObjectType;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.FileObjectService;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.FolderStorageService;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.FileObjectFacade;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.FolderFacade;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.*;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectBaseDTO;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectMiniDTO;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.mapper.FileObjectFullDTOMapper;
import ru.shulgindaniil.cloudFileStorage.security.UserDetailsImpl;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class FolderFacadeImpl implements FolderFacade {
    private final FolderStorageService folderStorageService;
    private final FileObjectService fileObjectService;
    private final FileObjectFullDTOMapper fileObjectFullDTOMapper;
    private final FileObjectFacade fileObjectFacade;

    @Override
    public FileObjectFullDTO getHome(UserDetailsImpl userDetails) {
        try {
            FileObjectDTO baseParent = fileObjectService.getBaseParent(userDetails.getId());
            return fileObjectFullDTOMapper.toTarget(baseParent);
        } catch(ResourceNotFoundException ex) {
            FileObjectDTO baseParent = fileObjectService.createBaseParent(userDetails.getId());
            FileObjectFullDTO fileObjectFullDto = fileObjectFullDTOMapper.toTarget(baseParent);
            folderStorageService.create(fileObjectFullDto);
            return fileObjectFullDto;
        }
    }

    @Override
    public FileObjectFullDTO create(UserDetailsImpl userDetails, FileObjectMiniDTO objectMiniDto) {
        objectMiniDto.setType(FileObjectType.FOLDER);
        FileObjectDTO fileObjectDto = fileObjectService.createFileObject(
            objectMiniDto,
            userDetails.getId(),
            0L
        );

        Collection<FileObjectBaseDTO> allParents = fileObjectService.getAllParents(fileObjectDto.getId());
        FileObjectFullDTO fileObjectFullDto = fileObjectFullDTOMapper.toTarget(fileObjectDto);
        fileObjectFullDto.setPath(allParents);

        folderStorageService.create(fileObjectFullDto);

        return fileObjectFullDto;
    }

    @Override
    public void delete(UserDetailsImpl userDetails, String folderId, boolean isRecursed) {
        FileObjectFullDTO fileObjectFullDto = fileObjectFacade.getFileObject(userDetails, folderId, FileObjectType.FOLDER);

        fileObjectService.deleteFileObject(folderId, userDetails.getId(), FileObjectType.FOLDER, isRecursed);
        folderStorageService.delete(fileObjectFullDto);
    }

    @Override
    public ByteArrayResource download(String folderId, UserDetailsImpl userDetails) {
        FileObjectFullDTO fileObjectFullDto = fileObjectFacade.getFileObject(userDetails, folderId, FileObjectType.FOLDER);
        return folderStorageService.download(fileObjectFullDto);
    }
}
