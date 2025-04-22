package com.repsy.hello.config;

import com.repsy.hello.service.storage.FileSystemStorageService;
import com.repsy.hello.service.storage.StorageService;
import com.repsy.hello.service.storage.ObjectStorageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Bean
    @ConditionalOnProperty(name = "storage.strategy", havingValue = "file-system")
    public StorageService fileSystemStorageService(FileSystemStorageService service) {
        return service;
    }

    // object-storage servisini sonra buraya ekleyeceğiz:
    @Bean
    @ConditionalOnProperty(name = "storage.strategy", havingValue = "object-storage")
    public StorageService objectStorageService(ObjectStorageService service) {
        return service;
    }
}
