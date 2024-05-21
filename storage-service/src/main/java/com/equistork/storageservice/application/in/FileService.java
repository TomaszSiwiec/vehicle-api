package com.equistork.storageservice.application.in;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {

    void uploadFile(MultipartFile file);
    InputStream downloadFile(String fileName);
}
