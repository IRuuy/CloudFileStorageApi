package ru.shulgindaniil.cloudFileStorage.objectStorage.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectFullDTO;

public interface FileStorageService {
    void create(FileObjectFullDTO fullFileObjectDto, MultipartFile file);
    void delete(FileObjectFullDTO fullFileObjectDto);
}
