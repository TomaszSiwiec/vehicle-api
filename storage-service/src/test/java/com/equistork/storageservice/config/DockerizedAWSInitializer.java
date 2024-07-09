package com.equistork.storageservice.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;

public class DockerizedAWSInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final DockerImageName AWS_IMAGE = DockerImageName.parse("localstack/localstack:3.5.0");

    public static final LocalStackContainer AWS_CONTAINER = new LocalStackContainer(AWS_IMAGE)
            .withServices(S3);

    @Override
    public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
        AWS_CONTAINER.start();
    }
}
