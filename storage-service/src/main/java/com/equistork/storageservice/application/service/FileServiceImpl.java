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
import java.util.UUID;

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
            UUID uuid = UUID.randomUUID();
            FileMetadata fileEntity = FileMetadata.builder()
                    .folderName(uuid.toString())
                    .size(file.getSize())
                    .filename(fileName)
                    .extension(getFileExtension(fileName))
                    .build();
            fileStorage.saveFile(fileEntity.getFileKey(), fileContent);
            fileRecordRepository.save(fileEntity);
            return fileEntity.getId();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    @Override
    public String downloadFile(long id) {
        FileMetadata file = fileRecordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("File with id = " + id + " not found"));
        return fileStorage.getFile(file.getFileKey());
    }

    @Override
    @Transactional
    public void deleteFile(long id) {
        FileMetadata file = fileRecordRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id = " + id + " not found"));
        fileRecordRepository.delete(file);
        fileStorage.deleteFile(file.getFileKey());
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
