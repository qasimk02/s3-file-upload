package com.aws.s3.imageUpload.Payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	private Long id;
	
	private String title;
	
	private String brand;
	
	private String description;
	
	private String quantity;
	
	private String price;
	
	private String category;
	
	private List<ImageDto> images;
	
}
