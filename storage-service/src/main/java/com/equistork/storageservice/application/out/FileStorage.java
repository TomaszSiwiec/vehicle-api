package com.equistork.storageservice.application.out;

import java.io.InputStream;

public interface FileStorage {

    void saveFile(String fileName, InputStream fileContent);
    InputStream getFile(long id);
}
