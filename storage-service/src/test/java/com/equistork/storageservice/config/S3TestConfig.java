package com.equistork.storageservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static com.equistork.storageservice.config.DockerizedAWSInitializer.AWS_CONTAINER;

@TestConfiguration
public class S3TestConfig {

    @Bean
    @Primary
    public AmazonS3 s3Client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(AWS_CONTAINER.getAccessKey(), AWS_CONTAINER.getSecretKey());
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(AWS_CONTAINER.getRegion()))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
