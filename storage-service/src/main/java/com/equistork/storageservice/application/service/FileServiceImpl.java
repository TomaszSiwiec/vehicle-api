package com.equistork.storageservice.application.service;

import com.equistork.storageservice.application.in.FileService;
import com.equistork.storageservice.application.out.FileStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
class FileServiceImpl implements FileService {

    private final FileStorage fileStorage;

    @Override
    public void uploadFile(MultipartFile file) {
        try (InputStream fileContent = file.getInputStream()) {
            fileStorage.saveFile(file.getOriginalFilename(), fileContent);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    @Override
    public InputStream downloadFile(String fileName) {
        return fileStorage.getFile(fileName);
    }
}
