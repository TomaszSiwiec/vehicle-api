package com.equistork.storageservice.it.config;

import com.equistork.storageservice.application.out.FileRecordRepository;
import com.equistork.storageservice.config.CucumberSpringContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

public class StorageContextConfiguration extends CucumberSpringContextConfiguration {

    @Autowired
    protected StorageScenarioContext storageScenarioContext;

    @Autowired
    protected FileRecordRepository fileRecordRepository;
}
