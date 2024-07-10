package com.equistork.storageservice.it.config;

//import lombok.data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

//@Data
@Component
public class StorageScenarioContext {

    private MultipartFile uploadFile;

    public MultipartFile getUploadFile() {

        return uploadFile;
    }

    public void setUploadFile(MultipartFile uploadFile) {

        this.uploadFile = uploadFile;
    }
}
