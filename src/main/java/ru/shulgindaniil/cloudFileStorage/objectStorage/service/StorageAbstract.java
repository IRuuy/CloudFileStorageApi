package ru.shulgindaniil.cloudFileStorage.objectStorage.service;

import io.minio.*;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.exception.FileOperationException;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectBaseDTO;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectFullDTO;

import java.io.InputStream;

@RequiredArgsConstructor
@Service
public abstract class StorageAbstract {
    protected final MinioClient minioClient;

    @Value("${spring.minio.bucket}")
    protected String bucketName;

    protected void deleteObject(String path) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(path)
                            .build()
            );
        } catch (Exception ex) {
            throw new FileOperationException();
        }

    }

    protected void createObject(String path, InputStream stream, Long size) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(path)
                    .stream(stream, size, -1)
                    .build()
            );
        } catch (Exception ex) {
            throw new FileOperationException();
        }
    }

    protected Iterable<Result<Item>> getAllObjects(String path) {
        return minioClient.listObjects(
                ListObjectsArgs.builder()
                        .bucket(bucketName)
                        .prefix(path)
                        .recursive(true)
                        .build()
        );
    }

    protected String createPath(FileObjectFullDTO fileObject) {
        StringBuilder path = new StringBuilder();
        String ownerId = fileObject.getOwner().id();

        for (int i = 0; i < 2; i++)
            path.append(ownerId.charAt(i)).append("/");

        path.append(ownerId).append("/");

        for(FileObjectBaseDTO pathItem : fileObject.getPath())
            path.append(pathItem.getName()).append("/");

        path.append(fileObject.getName());

        return path.toString();
    }
}
