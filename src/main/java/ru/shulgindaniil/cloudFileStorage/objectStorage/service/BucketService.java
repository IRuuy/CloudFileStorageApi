package ru.shulgindaniil.cloudFileStorage.objectStorage.service;

public interface BucketService {
    void createBucket(String bucketName);
    boolean isBucketExists(String bucketName);
}
