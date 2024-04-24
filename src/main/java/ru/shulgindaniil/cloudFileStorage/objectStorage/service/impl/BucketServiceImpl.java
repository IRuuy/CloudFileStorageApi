package ru.shulgindaniil.cloudFileStorage.objectStorage.service.impl;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.BucketService;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {
    private final MinioClient minioClient;

    @Override
    @SneakyThrows
    public void createBucket(String bucketName) {
        minioClient.makeBucket(MakeBucketArgs.builder()
                .bucket(bucketName)
                .build());
    }

    @Override
    @SneakyThrows
    public boolean isBucketExists(String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
    }
}
