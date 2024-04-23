package ru.shulgindaniil.cloudFileStorage.objectStorage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.minio.client")
public record MinioProps (
        String endpoint,
        String accessKey,
        String secretKey
) {
}

