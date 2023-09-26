package com.aws.s3.imageUpload.Controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aws.s3.imageUpload.Payload.ProductDto;
import com.aws.s3.imageUpload.Service.ProductService;
import com.aws.s3.imageUpload.Service.S3Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private S3Service s3Service;
	

	@PostMapping("")
	public ResponseEntity<ProductDto> addProduct(@RequestPart("productDetails") String p ,@RequestPart("image") MultipartFile image) throws JsonMappingException, JsonProcessingException {
		
		ProductDto productData = new ObjectMapper().readValue(p, ProductDto.class);
		ProductDto addedProduct = this.productService.addProduct(productData,image);
		return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
		ProductDto p = this.productService.findProductById(id);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> ps = this.productService.findAllProducts();
		return new ResponseEntity<>(ps, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto p, @PathVariable("id") Long id) {
		ProductDto addedProduct = this.productService.updateProduct(p, id);
		return new ResponseEntity<>(addedProduct, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
		this.productService.deleteProduct(id);
		ArrayList<String> resp = new ArrayList<>();
		resp.add("Product Deleted Successfully of Id " + id);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("File is empty");
		}
		return new ResponseEntity<>(this.s3Service.uploadFile(file, 123L,"products",file.getOriginalFilename()), HttpStatus.CREATED);
	}
}
