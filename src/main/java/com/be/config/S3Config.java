package com.be.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.regions.Region;

//@Configuration
//public class S3Config {
//    @Value("${cloud.aws.s3.credentials.access-key}")
//    private String accessKey;
//
//    @Value("${cloud.aws.s3.credentials.secret-key}")
//    private String secretKey;
//
//    @Bean
//    public S3Client s3client() {
//        Region region = Region.AP_NORTHEAST_2;
//        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
//        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);
//        return S3Client.builder()
//                .region(region)
//                .credentialsProvider(credentialsProvider)
//                .build();
//    }
//}
