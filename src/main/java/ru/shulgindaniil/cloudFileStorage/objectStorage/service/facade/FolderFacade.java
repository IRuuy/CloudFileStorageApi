package ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade;

import org.springframework.core.io.ByteArrayResource;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectDTO;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectFullDTO;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectMiniDTO;
import ru.shulgindaniil.cloudFileStorage.security.UserDetailsImpl;

public interface FolderFacade {
    FileObjectFullDTO create(UserDetailsImpl userDetails, FileObjectMiniDTO objectMiniDto);
    FileObjectDTO getHome(UserDetailsImpl userDetails);
    void delete(UserDetailsImpl userDetails, String folderId, boolean isRecursed);
    ByteArrayResource download(String folderId, UserDetailsImpl userDetails);
}
