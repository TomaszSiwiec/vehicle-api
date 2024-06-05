package com.equistork.storageservice.application.service;

import com.equistork.storageservice.application.in.FileService;
import com.equistork.storageservice.application.out.FileRecordRepository;
import com.equistork.storageservice.application.out.FileStorage;
import com.equistork.storageservice.domain.model.FileMetadata;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
class FileServiceImpl implements FileService {

    private final FileStorage fileStorage;
    private final FileRecordRepository fileRecordRepository;

    @Override
    @Transactional
    public Long uploadFile(MultipartFile file) {
        try (InputStream fileContent = file.getInputStream()) {
            String fileName = file.getOriginalFilename();
            FileMetadata fileEntity = FileMetadata.builder()
                    .size(file.getSize())
                    .filename(fileName)
                    .extension(getFileExtension(fileName))
                    .build();
            fileStorage.saveFile(file.getOriginalFilename(), fileContent);
            fileRecordRepository.save(fileEntity);
            return fileEntity.getId();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    @Override
    public InputStream downloadFile(long id) {
        return fileStorage.getFile(id);
    }

    @Override
    public void deleteFile(long id) {
        FileMetadata file = fileRecordRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id = " + id + " not found"));
        fileRecordRepository.delete(file);
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1) {
            return "";
        }

        return fileName.substring(lastDotIndex + 1);
    }
}
