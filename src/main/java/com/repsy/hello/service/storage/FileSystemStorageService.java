package com.repsy.hello.service.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileSystemStorageService implements StorageService {

    @Value("${storage.filesystem.path}")
    private String rootPath;

    @Override
    public void saveFile(String packageName, String version, String fileName, byte[] content) throws Exception {
        File dir = new File(rootPath + "/" + packageName + "/" + version);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content);
        }
    }

    @Override
    public byte[] readFile(String packageName, String version, String fileName) throws Exception {
        Path filePath = Path.of(rootPath, packageName, version, fileName);
        return Files.readAllBytes(filePath);
    }

}
