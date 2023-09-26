package com.aws.s3.imageUpload.Payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ImageDto {
	
	private Long id;
	private String imageName;
	private String createdAt;
	
}
