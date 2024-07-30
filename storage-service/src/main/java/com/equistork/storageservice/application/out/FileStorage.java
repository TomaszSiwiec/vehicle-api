package com.equistork.storageservice.application.out;

import java.io.InputStream;

public interface FileStorage {

    void saveFile(String fileKey, InputStream fileContent);

    String getFile(String fileKey);

    void deleteFile(String fileKey);
}
