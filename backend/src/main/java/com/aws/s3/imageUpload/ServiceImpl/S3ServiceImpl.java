package com.aws.s3.imageUpload.ServiceImpl;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aws.s3.imageUpload.Service.S3Service;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
@Service
public class S3ServiceImpl implements S3Service {

	@Value("${application.bucket.name}")
	private String bucketName;

	@Autowired
	private S3Client s3Client;

	public String uploadFile(MultipartFile file, Long id, String folder, String fileName) {

		String baseName = fileName.substring(0, fileName.lastIndexOf("."));
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

		// Small image
		byte[] smallImage = resizeImageToBytes(file, 300, (1.33), 0.8);
		uploadToS3(smallImage, folder + "/" + id + "/" + baseName + "-small." + extension);

		// Medium image
		byte[] mediumImage = resizeImageToBytes(file, 700, (1.33), 0.8);
		uploadToS3(mediumImage, folder + "/" + id + "/" + baseName + "-medium." + extension);

		// Large image
		byte[] largeImage = resizeImageToBytes(file, 1200, (1.33), 0.8);
		uploadToS3(largeImage, folder + "/" + id + "/" + baseName + "-large." + extension);

		return "File uploaded succesfully " + fileName;
	}

	// upload to s3
	private String uploadToS3(byte[] fileBytes, String fileName) {
		PutObjectRequest objectRequest = PutObjectRequest.builder().bucket(bucketName).key(fileName).build();
		s3Client.putObject(objectRequest, RequestBody.fromBytes(fileBytes));
		return "File Uploaded Succesfully " + fileName;
	}

//	//resize image
	private byte[] resizeImageToBytes(MultipartFile file, int width, double ratio, double quality) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			Thumbnails.of(file.getInputStream()).forceSize(width, (int)(width*ratio)).outputQuality(quality)
					.toOutputStream(outputStream);
		} catch (Exception ex) {
			log.error("Error resizing the image", ex);
		}
		return outputStream.toByteArray();
	}

}
