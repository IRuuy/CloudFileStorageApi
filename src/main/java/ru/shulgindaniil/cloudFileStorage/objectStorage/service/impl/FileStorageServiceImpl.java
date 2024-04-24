package ru.shulgindaniil.cloudFileStorage.objectStorage.service.impl;

import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.exception.FileOperationException;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.StorageAbstract;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.FileStorageService;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectFullDTO;

import java.io.InputStream;

@Service
public class FileStorageServiceImpl extends StorageAbstract implements FileStorageService {
    public FileStorageServiceImpl(MinioClient minioClient) {
        super(minioClient);
    }

    @Override
    public void create(FileObjectFullDTO fullFileObjectDto, MultipartFile file) {
        String path = createPath(fullFileObjectDto);

        try (InputStream stream = file.getInputStream()) {
            createObject(path, stream, file.getSize());
        } catch (Exception e) {
            throw new FileOperationException();
        }
    }

    @Override
    public void delete(FileObjectFullDTO fullFileObjectDto) {
        String path = createPath(fullFileObjectDto);
        deleteObject(path);
    }
}
