package com.repsy.hello.service.storage;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.net.URI;

@Slf4j
@Service
public class ObjectStorageService implements StorageService {

    private S3Client s3;

    @Value("${storage.object.endpoint}")
    private String endpoint;

    @Value("${storage.object.accessKey}")
    private String accessKey;

    @Value("${storage.object.secretKey}")
    private String secretKey;

    @Value("${storage.object.bucket}")
    private String bucketName;

    @PostConstruct
    public void init() {

        s3 = S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .region(Region.US_EAST_1)
                .build();

        try {
            s3.headBucket(HeadBucketRequest.builder().bucket(bucketName).build());
        } catch (NoSuchBucketException e) {
            s3.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
        }
    }

    @Override
    public void saveFile(String packageName, String version, String fileName, byte[] content) {
        String key = packageName + "/" + version + "/" + fileName;
        s3.putObject(PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build(),
                RequestBody.fromBytes(content));
    }

    @Override
    public byte[] readFile(String packageName, String version, String fileName) throws Exception {
        String key = packageName + "/" + version + "/" + fileName;
        return s3.getObject(GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build())
                .readAllBytes();
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

}
