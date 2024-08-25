package com.springredis.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springredis.cache.dto.ProductDTO;
import com.springredis.cache.exceptions.DataNotFoundException;
import com.springredis.cache.service.ProductService;


@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(path = "/{productid}")
	public ProductDTO getProductById(@PathVariable(name = "productid") String productId) throws DataNotFoundException {
		
			return productService.getProduct(Integer.parseInt(productId));
	}
	
	@PostMapping()
	public void addProduct(@RequestBody ProductDTO productDTO) {
		
			 productService.createProduct(productDTO);
	}
	
	@PatchMapping(path = "/{productid}")
	public void updateProduct(@PathVariable(name = "productid") String productId, @RequestBody ProductDTO productDTO) throws DataNotFoundException {
		
			 productService.updateProduct(Integer.parseInt(productId), productDTO);
	}
	
	@DeleteMapping(path = "/{productid}")
	public void deleteProductById(@PathVariable String productid) throws DataNotFoundException {
		
			 productService.deleteProduct(Integer.parseInt(productid));
	}
	

}
