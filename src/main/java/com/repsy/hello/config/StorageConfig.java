package com.repsy.hello.config;

import com.repsy.hello.service.storage.FileSystemStorageService;
import com.repsy.hello.service.storage.StorageService;
import com.repsy.hello.service.storage.ObjectStorageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class StorageConfig {

    @Bean(name = "storageService")
    @ConditionalOnProperty(name = "storage.strategy", havingValue = "file-system")
    public StorageService fileSystemStorageService(FileSystemStorageService service) {
        return service;
    }

    @Bean(name = "storageService")
    @ConditionalOnProperty(name = "storage.strategy", havingValue = "object-storage")
    public StorageService objectStorageService(
            @Value("${storage.object.endpoint}") String endpoint,
            @Value("${storage.object.accessKey}") String accessKey,
            @Value("${storage.object.secretKey}") String secretKey,
            @Value("${storage.object.bucket}") String bucketName) {
        ObjectStorageService service = new ObjectStorageService();
        service.setEndpoint(endpoint);
        service.setAccessKey(accessKey);
        service.setSecretKey(secretKey);
        service.setBucketName(bucketName);
        return service;
    }
}
