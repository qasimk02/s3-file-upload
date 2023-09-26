package com.aws.s3.imageUpload.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aws.s3.imageUpload.Payload.ProductDto;

public interface ProductService {
	
	ProductDto addProduct(ProductDto product,MultipartFile file);
	
	ProductDto findProductById(Long pId);
	
	List<ProductDto> findAllProducts();
	
	ProductDto updateProduct(ProductDto product,Long id);
	
	void deleteProduct(Long pId);
	
}
