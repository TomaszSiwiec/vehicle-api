package com.equistork.storageservice.it.steps;

import com.equistork.storageservice.it.config.StorageContextConfiguration;
import io.cucumber.java.en.Given;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.equistork.storageservice.it.steps")
public class S3Scenario extends StorageContextConfiguration {

    @Given("prepare image with name duck.jpg to upload")
    public void prepareImageToUpload() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("files/duck.jpg").getFile());
        MultipartFile multipartFile = convertFileToMultipart(file);
        storageScenarioContext.setUploadFile(multipartFile);
    }

    private MultipartFile convertFileToMultipart(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        return new MockMultipartFile("file", file.getName(), "text/plain", input);
    }
}
