package ru.shulgindaniil.cloudFileStorage.objectStorage.service;

import org.springframework.core.io.ByteArrayResource;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectFullDTO;

public interface FolderStorageService {
    void create(FileObjectFullDTO fullFileObjectDto);
    void delete(FileObjectFullDTO fullFileObjectDto);
    ByteArrayResource download(FileObjectFullDTO fullFileObjectDto);
}
