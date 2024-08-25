package com.springredis.cache.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springredis.cache.dto.ProductDTO;
import com.springredis.cache.exceptions.DataNotFoundException;
import com.springredis.cache.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Cacheable(value = "product", key = "#productDTO.productId")
	public void createProduct(ProductDTO productDTO) {
		
		this.productRepository.save(productDTO);
		
	}
	
	@Cacheable(value = "product", key = "#productId")
	public ProductDTO getProduct(Integer productId) throws DataNotFoundException {
		
		log.info("Calling DB to fetch product");
		
		Optional<ProductDTO> productDTO = this.productRepository.findById(productId);
		
		if( !productDTO.isEmpty()) {
			
			return productDTO.get();
			
		}
			
		
		throw new DataNotFoundException("Product not available");
		
	}
	
	@CachePut(value = "product", key = "#productId")
	public ProductDTO updateProduct(Integer productId, ProductDTO productDTO) {
		
		if (productRepository.existsById(productId)) {
			
			log.info("update in DB");
			productDTO.setProductId(productId);
			this.productRepository.save(productDTO);
			return productDTO;
		}
			
		
	 throw new DataNotFoundException("Product not available");
		
	}
	
	@CacheEvict(cacheNames="product", key="#productId") 
	public void deleteProduct(Integer productId) throws DataNotFoundException {
		
		if (!this.productRepository.existsById(productId)) {
			
			
			 throw new DataNotFoundException("Product not available");
		}
		
		this.productRepository.deleteById(productId);
		
	}
}
