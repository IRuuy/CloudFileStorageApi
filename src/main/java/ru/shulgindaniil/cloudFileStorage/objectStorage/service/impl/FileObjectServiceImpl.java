package ru.shulgindaniil.cloudFileStorage.objectStorage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.shulgindaniil.cloudFileStorage.common.exception.ResourceNotFoundException;
import ru.shulgindaniil.cloudFileStorage.common.util.UniqueIdGenerator;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObject;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObjectType;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.exception.FileObjectAlreadyExistException;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.exception.DirectoryNotEmptyException;
import ru.shulgindaniil.cloudFileStorage.objectStorage.repository.FileObjectRepository;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.FileObjectService;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectBaseDTO;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectDTO;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectMiniDTO;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.mapper.FileObjectBaseDTOMapper;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.mapper.FileObjectDTOMapper;
import ru.shulgindaniil.cloudFileStorage.user.domain.entity.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileObjectServiceImpl implements FileObjectService {
    private final FileObjectRepository fileObjectRepository;

    private final UniqueIdGenerator<FileObject> uniqueIdGenerator;
    private final FileObjectBaseDTOMapper fileObjectBaseDtoMapper;
    private final FileObjectDTOMapper fileObjectDtoMapper;

    @Value("${app.countBytesToGenerateObjectId}")
    private Integer countBytesToGenerateObjectId;

    @Override
    public FileObjectDTO createFileObject(FileObjectMiniDTO fileObjectMiniDto, String ownerId, Long size) {
        return fileObjectRepository.findByIdAndOwnerIdAndType (
                fileObjectMiniDto.getParentId(), ownerId, FileObjectType.FOLDER
            ).map(parentObject -> {
                    checkOnExistFileObject(
                            fileObjectMiniDto.getParentId(),
                            fileObjectMiniDto.getName(),
                            fileObjectMiniDto.getType()
                    );

                    parentObject.setSize(parentObject.getSize() + 1);

                    FileObject entity = FileObject.builder()
                            .id(uniqueIdGenerator.generate(fileObjectRepository, countBytesToGenerateObjectId))
                            .size(size)
                            .parent(parentObject)
                            .type(fileObjectMiniDto.getType())
                            .name(fileObjectMiniDto.getName())
                            .owner(new User(parentObject.getOwner().getId()))
                            .build();

                    return fileObjectDtoMapper.toTarget(fileObjectRepository.save(entity));
            }).orElseThrow(() -> new ResourceNotFoundException(fileObjectMiniDto.getParentId()));
    }

    @Override
    public FileObjectDTO createBaseParent(String ownerId) {
        checkOnExistFileObject(null, "home", FileObjectType.FOLDER);

        FileObject entity = FileObject.builder()
                .id(uniqueIdGenerator.generate(fileObjectRepository, countBytesToGenerateObjectId))
                .size(0L)
                .parent(null)
                .type(FileObjectType.FOLDER)
                .name("home")
                .owner(new User(ownerId))
                .build();

        return fileObjectDtoMapper.toTarget(fileObjectRepository.save(entity));
    }

    @Override
    public FileObjectDTO getFileObject(String fileObjectId, String ownerId, FileObjectType type) {
        FileObject fileObject = fileObjectRepository.findByIdAndOwnerIdAndType(fileObjectId, ownerId, type)
                .orElseThrow(ResourceNotFoundException::new);

        return fileObjectDtoMapper.toTarget(fileObject);
    }

    @Override
    public FileObjectDTO getBaseParent(String ownerId) {
        FileObject fileObject = fileObjectRepository.findBaseParent(ownerId)
                .orElseThrow(ResourceNotFoundException::new);
        return fileObjectDtoMapper.toTarget(fileObject);
    }

    @Override
    public Collection<FileObjectBaseDTO> getAllParents(String fileObjectId) {
        Collection<FileObjectBaseDTO> parents = fileObjectBaseDtoMapper.toTarget(
                fileObjectRepository.getPath(fileObjectId)
        );
        Collections.reverse((List<FileObjectBaseDTO>) parents);
        return parents;
    }

    @Override
    public Collection<FileObjectDTO> getAllChildren(String fileObjectId, String ownerId) {
        Collection<FileObject> items =
                fileObjectRepository.getAllChildren(fileObjectId, ownerId);

        return fileObjectDtoMapper.toTarget(items);
    }

    @Override
    public void deleteFileObject(String folderId, String ownerId, FileObjectType type, boolean isRecursed) {
        fileObjectRepository.findByIdAndOwnerIdAndType(folderId, ownerId, type)
            .ifPresentOrElse(fileObject -> {
                if(!isRecursed && fileObjectRepository.hashChild(folderId))
                    throw new DirectoryNotEmptyException();

                FileObject parent = fileObject.getParent();
                parent.setSize(parent.getSize()-1);

                fileObjectRepository.delete(fileObject);
            },
            ResourceNotFoundException::new
        );
    }

    private void checkOnExistFileObject(String parentId, String name, FileObjectType type) {
        if(fileObjectRepository.existFileObject(parentId,name,type.name()))
            throw new FileObjectAlreadyExistException("home");
    }
}
