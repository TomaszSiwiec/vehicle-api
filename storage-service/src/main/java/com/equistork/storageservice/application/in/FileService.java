package com.equistork.storageservice.application.in;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    Long uploadFile(MultipartFile file);

    String downloadFile(long id);

    void deleteFile(long id);
}
