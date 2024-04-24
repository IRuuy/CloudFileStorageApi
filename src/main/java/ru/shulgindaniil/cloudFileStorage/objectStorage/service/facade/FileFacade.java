package ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectFullDTO;
import ru.shulgindaniil.cloudFileStorage.security.UserDetailsImpl;

public interface FileFacade {
    FileObjectFullDTO create(MultipartFile file, String parentId, UserDetailsImpl userDetails);
    void delete(String fileId, UserDetailsImpl userDetails);
}
