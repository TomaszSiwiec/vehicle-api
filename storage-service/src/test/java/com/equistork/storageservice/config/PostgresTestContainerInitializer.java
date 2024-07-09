package com.equistork.storageservice.config;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.images.PullPolicy;
import org.testcontainers.utility.DockerImageName;

import static org.testcontainers.containers.PostgreSQLContainer.POSTGRESQL_PORT;

@Slf4j
public class PostgresTestContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final DockerImageName DOCKER_IMAGE_NAME = DockerImageName.parse("postgres:14.5-alpine");

    public static PostgreSQLContainer<?> container;

    @Override
    public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
        container = new PostgreSQLContainer<>(DOCKER_IMAGE_NAME);
        container.withImagePullPolicy(PullPolicy.alwaysPull());
        container.withExposedPorts(POSTGRESQL_PORT);
        container.withUsername("postgres")
                .withPassword("postgres")
                .withDatabaseName("postgres")
                .start();

        log.warn("TestContainers Postgres JDBC: {}",
                container.getJdbcUrl() + "&user=%s&password=%s".formatted(container.getUsername(), container.getPassword()));

        TestPropertyValues.of(
                        "spring.datasource.url=" + container.getJdbcUrl(),
                        "spring.datasource.username=" + container.getUsername(),
                        "spring.datasource.password=" + container.getPassword()
                )
                .applyTo(applicationContext.getEnvironment());
    }
}
