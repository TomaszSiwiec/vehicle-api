package com.equistork.storageservice.application.in;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {

    Long uploadFile(MultipartFile file);
    InputStream downloadFile(long id);
    void deleteFile(long id);
}
