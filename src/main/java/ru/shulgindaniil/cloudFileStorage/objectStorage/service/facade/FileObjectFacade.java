package ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade;

import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObjectType;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectFullDTO;
import ru.shulgindaniil.cloudFileStorage.security.UserDetailsImpl;

public interface FileObjectFacade {
    FileObjectFullDTO getFileObject(UserDetailsImpl userDetails, String fileId, FileObjectType type);
}
