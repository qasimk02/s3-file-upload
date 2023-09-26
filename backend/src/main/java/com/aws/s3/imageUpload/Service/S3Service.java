package com.aws.s3.imageUpload.Service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
	
	public String uploadFile(MultipartFile file, Long id,String folder, String fileName);
	
}
