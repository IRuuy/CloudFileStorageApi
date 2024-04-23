package ru.shulgindaniil.cloudFileStorage.objectStorage.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(value = {MinioProps.class})
public class MinioConfig {
    private final MinioProps minioProps;

    @Bean
    public MinioClient configuredMinioClient() {
        return MinioClient.builder()
                .endpoint(minioProps.endpoint())
                .credentials(minioProps.accessKey(), minioProps.secretKey())
                .build();
    }
}
