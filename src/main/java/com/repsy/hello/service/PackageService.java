package com.repsy.hello.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsy.hello.dto.PackageMetaDto;
import com.repsy.hello.entity.Dependency;
import com.repsy.hello.entity.Package;
import com.repsy.hello.repository.PackageRepository;
import com.repsy.hello.service.storage.StorageService;
// import com.repsy.hello.service.storage.ObjectStorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
// import java.util.List;

@Service
public class PackageService {

    private final StorageService storageService;
    private final PackageRepository packageRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${storage.strategy}")
    private String storageType;

    public PackageService(@Qualifier("storageService") StorageService storageService,
            PackageRepository packageRepository) {
        this.storageService = storageService;
        this.packageRepository = packageRepository;
    }

    public void handleUpload(String packageName, String version,
            MultipartFile repFile, MultipartFile metaFile) throws Exception {

        if (!repFile.getOriginalFilename().endsWith(".rep")) {
            throw new IllegalArgumentException("Only .rep files are accepted.");
        }

        PackageMetaDto meta = objectMapper.readValue(metaFile.getBytes(), PackageMetaDto.class);

        if (!meta.getName().equals(packageName) || !meta.getVersion().equals(version)) {
            throw new IllegalArgumentException("Metadata does not match path.");
        }

        storageService.saveFile(packageName, version, "package.rep", repFile.getBytes());
        storageService.saveFile(packageName, version, "meta.json", metaFile.getBytes());

        Package pkg = Package.builder()
                .name(meta.getName())
                .version(meta.getVersion())
                .author(meta.getAuthor())
                .dependencies(meta.getDependencies().stream()
                        .map(d -> new Dependency(d.getPackageName(), d.getVersion()))
                        .toList())
                .uploadedAt(LocalDateTime.now())
                .storageType(storageType)
                .build();

        packageRepository.save(pkg);
    }

    public byte[] getFile(String packageName, String version, String fileName) throws Exception {
        try {
            return storageService.readFile(packageName, version, fileName);
        } catch (IOException e) {
            throw new FileNotFoundException("File not found: " + fileName);
        }
    }
}
