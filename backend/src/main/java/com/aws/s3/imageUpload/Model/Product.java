package com.aws.s3.imageUpload.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private Long id;
	
	@Column(name="title",nullable=false)
	private String title;
	
	@Column(name="brand",nullable=false)
	private String brand;
	
	@Column(name="description",nullable=false,length=5000)
	private String description;
	
	@Column(name="quantity",nullable=false)
	private String quantity;
	
	@Column(name="price",nullable=false)
	private String price;
	
	@Column(name="category",nullable=false)
	private String category;
	
	//mappings
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Image> images;
	
}
