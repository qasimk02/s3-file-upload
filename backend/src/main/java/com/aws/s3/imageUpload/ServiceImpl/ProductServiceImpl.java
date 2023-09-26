package com.aws.s3.imageUpload.ServiceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aws.s3.imageUpload.Model.Image;
import com.aws.s3.imageUpload.Model.Product;
import com.aws.s3.imageUpload.Payload.ProductDto;
import com.aws.s3.imageUpload.Repository.ProductRepository;
import com.aws.s3.imageUpload.Service.ProductService;
import com.aws.s3.imageUpload.Service.S3Service;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private S3Service s3Service;

	@Override
	@Transactional
	public ProductDto addProduct(ProductDto productDto, MultipartFile file) {
		Product p = this.modelMapper.map(productDto, Product.class);
		
		String fileName = UUID.randomUUID().toString()+file.getOriginalFilename();
		
		Image image = new Image();
		image.setImageName(fileName);
		image.setProduct(p);
		p.setImages(List.of(image));
		
		Product savedP = this.productRepository.save(p);
		s3Service.uploadFile(file,savedP.getId(),"products",fileName);
		
		return this.modelMapper.map(savedP, ProductDto.class);
	}

	@Override
	public ProductDto findProductById(Long pId) {
		Product p = this.productRepository.findById(pId)
				.orElseThrow(() -> new RuntimeException("Product Not fournd with Id " + pId));
		return this.modelMapper.map(p, ProductDto.class);
	}

	@Override
	public List<ProductDto> findAllProducts() {
		List<Product> allProducts = this.productRepository.findAll();
		List<ProductDto> allPs = allProducts.stream().map((p) -> this.modelMapper.map(p, ProductDto.class))
				.collect(Collectors.toList());
		return allPs;
	}

	@Override
	public ProductDto updateProduct(ProductDto product, Long id) {
		Product p = this.productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product Not fournd with Id " + id));

		p.setPrice(product.getPrice());
		p.setQuantity(product.getQuantity());
		p.setTitle(product.getTitle());
		p.setCategory(product.getCategory());

		Product updatedProduct = this.productRepository.save(p);
		ProductDto pDto = this.modelMapper.map(updatedProduct, ProductDto.class);
		return pDto;
	}

	@Override
	public void deleteProduct(Long pId) {
		Product p = this.productRepository.findById(pId)
				.orElseThrow(() -> new RuntimeException("Product not found with id " + pId));
		this.productRepository.delete(p);
	}

}
