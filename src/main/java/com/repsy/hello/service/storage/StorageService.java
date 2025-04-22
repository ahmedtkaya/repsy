package com.repsy.hello.service.storage;

public interface StorageService {
    void saveFile(String packageName, String version, String fileName, byte[] content) throws Exception;

    byte[] readFile(String packageName, String version, String fileName) throws Exception;
}
