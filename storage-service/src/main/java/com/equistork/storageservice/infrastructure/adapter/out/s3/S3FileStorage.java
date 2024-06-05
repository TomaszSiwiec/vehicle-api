package com.equistork.storageservice.infrastructure.adapter.out.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.equistork.storageservice.application.out.FileRecordRepository;
import com.equistork.storageservice.application.out.FileStorage;
import com.equistork.storageservice.domain.model.FileMetadata;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
class S3FileStorage implements FileStorage {

    private final AmazonS3 amazonS3;
    private final FileRecordRepository repository;
    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Override
    public void saveFile(String fileName, InputStream fileContent) {
        ObjectMetadata metadata = new ObjectMetadata();
        amazonS3.putObject(bucketName, fileName, fileContent, metadata);
    }

    @Override
    public InputStream getFile(long id) {
        FileMetadata file = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("File with id = " + id + " not found"));
        return amazonS3.getObject(bucketName, file.getFilename()).getObjectContent();
    }
}
