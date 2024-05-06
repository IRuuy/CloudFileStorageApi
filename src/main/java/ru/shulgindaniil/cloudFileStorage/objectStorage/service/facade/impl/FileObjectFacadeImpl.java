package ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObjectType;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.FileObjectService;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.FileObjectFacade;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectDTO;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectFullDTO;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.mapper.FileObjectFullDTOMapper;
import ru.shulgindaniil.cloudFileStorage.security.UserDetailsImpl;

@Service
@RequiredArgsConstructor
public class FileObjectFacadeImpl implements FileObjectFacade {
    protected final FileObjectService fileObjectService;
    protected final FileObjectFullDTOMapper fileObjectFullDTOMapper;

    public FileObjectFullDTO getFileObject(UserDetailsImpl userDetails, String fileId, FileObjectType type) {
        FileObjectDTO fileObject = fileObjectService.getFileObject(fileId, userDetails.getId(), type);
        FileObjectFullDTO fileObjectFullDto = fileObjectFullDTOMapper.toTarget(fileObject);

        if(fileObject.getParent() != null){
            fileObjectFullDto.setPath(fileObjectService.getRecursedParents(fileObject.getId()));
        }

        return fileObjectFullDto;
    }
}
