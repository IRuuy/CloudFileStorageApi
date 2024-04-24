package ru.shulgindaniil.cloudFileStorage.objectStorage.service.impl;

import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.exception.FileOperationException;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.StorageAbstract;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.FolderStorageService;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectFullDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FolderStorageServiceImpl extends StorageAbstract implements FolderStorageService {
    public FolderStorageServiceImpl(MinioClient minioClient) {
        super(minioClient);
    }
    @Override
    public void create(FileObjectFullDTO fullFileObjectDto) {
        String path = createPath(fullFileObjectDto) + "/";
        createObject(path, new ByteArrayInputStream(new byte[] {}), 0L);
    }

    @Override
    @SneakyThrows
    public void delete(FileObjectFullDTO fullFileObjectDto) {
        String path = createPath(fullFileObjectDto);

        for (Result<Item> result : getAllObjects(path)) {
            deleteObject(result.get().objectName());
        }

        deleteObject(path);
    }
}
