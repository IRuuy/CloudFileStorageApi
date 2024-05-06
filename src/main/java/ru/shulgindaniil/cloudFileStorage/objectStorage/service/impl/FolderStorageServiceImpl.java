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

    public ByteArrayResource download(FileObjectFullDTO fullFileObjectDto) {
        String path = createPath(fullFileObjectDto) + "/";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            addFilesToZip(path, zipOutputStream);
        } catch (Exception e) {
            throw new FileOperationException();
        }
        return new ByteArrayResource(outputStream.toByteArray());
    }

    @SneakyThrows
    private void addFilesToZip(String path, ZipOutputStream zipOutputStream) {
        Iterable<Result<Item>> allObjects = getAllObjects(path);
        for (Result<Item> object : allObjects) {
            String objectName = object.get().objectName();

            if (objectName.equals(path) && !path.isEmpty())
                continue;

            ZipEntry entry = new ZipEntry(objectName.substring(path.length()));
            zipOutputStream.putNextEntry(entry);

            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();

            try (GetObjectResponse response = minioClient.getObject(getObjectArgs)) {
                IOUtils.copy(response, zipOutputStream);
            }

            zipOutputStream.closeEntry();
        }
    }
}
