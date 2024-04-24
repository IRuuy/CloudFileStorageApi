package ru.shulgindaniil.cloudFileStorage.objectStorage.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.BucketService;

@Configuration
@RequiredArgsConstructor
public class MinioBucketConfig {
    private final BucketService bucketService;

    @Value("${spring.minio.bucket}")
    private String bucketName;

    @EventListener(ContextRefreshedEvent.class)
    public void createBucket() {
        try {
            if (!bucketService.isBucketExists(bucketName)) {
                bucketService.createBucket(bucketName);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Bucket '" + bucketName + "' is not created", e);
        }
    }
}
