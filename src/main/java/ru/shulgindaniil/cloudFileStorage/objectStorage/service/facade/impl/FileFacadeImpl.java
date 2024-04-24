package ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObjectType;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.FileObjectService;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.FileStorageService;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.FileFacade;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.FileObjectFacade;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.*;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.mapper.FileObjectFullDTOMapper;
import ru.shulgindaniil.cloudFileStorage.security.UserDetailsImpl;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class FileFacadeImpl implements FileFacade {
    private final FileStorageService fileStorageService;
    private final FileObjectService fileObjectService;
    private final FileObjectFullDTOMapper fileObjectFullDTOMapper;
    private final FileObjectFacade fileObjectFacade;
    @Override
    public void delete(String fileId, UserDetailsImpl userDetails) {
        FileObjectFullDTO fileObjectFullDto = fileObjectFacade.getFileObject(userDetails, fileId, FileObjectType.FILE);

        fileObjectService.deleteFileObject(fileId, userDetails.getId(), FileObjectType.FILE,false);
        fileStorageService.delete(fileObjectFullDto);
    }

    @Override
    public FileObjectFullDTO create(MultipartFile file, String parentId, UserDetailsImpl userDetails) {
        FileObjectDTO fileObjectDto = fileObjectService.createFileObject(
                new FileObjectMiniDTO(file.getOriginalFilename(), FileObjectType.FILE, parentId),
                userDetails.getId(),
                file.getSize()
        );

        Collection<FileObjectBaseDTO> allParents = fileObjectService.getAllParents(fileObjectDto.getId());
        FileObjectFullDTO fileObjectFullDto = fileObjectFullDTOMapper.toTarget(fileObjectDto);
        fileObjectFullDto.setPath(allParents);

        fileStorageService.create(fileObjectFullDto, file);

        return fileObjectFullDto;
    }
}
