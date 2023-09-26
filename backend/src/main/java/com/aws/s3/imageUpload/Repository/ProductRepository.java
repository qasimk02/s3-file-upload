package com.aws.s3.imageUpload.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aws.s3.imageUpload.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
