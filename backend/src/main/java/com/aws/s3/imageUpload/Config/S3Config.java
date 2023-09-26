package com.aws.s3.imageUpload.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

	@Value("${cloud.aws.credentials.access-key}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secret-key}")
	private String secretKey;

	@Value("${cloud.aws.region.static}")
	private String region;

	private AwsCredentialsProvider credentials() {
		AwsCredentialsProvider credentials = StaticCredentialsProvider
				.create(AwsBasicCredentials.create(accessKey, secretKey));
		return credentials;
	}

	@Bean
	public S3Client s3Client() {
		S3Client s3Client = S3Client.builder().region(Region.of(region)).credentialsProvider(credentials()).build();
		return s3Client;
	}
}
