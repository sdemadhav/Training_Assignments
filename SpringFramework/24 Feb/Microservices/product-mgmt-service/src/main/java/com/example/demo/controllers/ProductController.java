package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService ps;
	
	@GetMapping("/products")
	public List<Product> getProducts(){
		return ps.getAllProducts();
	}
	
	@GetMapping("/products/{id}")
	public Optional<Product> getProductById(@PathVariable int id){
		return ps.getProductById(id);
	}
	@PostMapping("/products")
	public String addProducts(@RequestBody Product p) {
		return ps.addProduct(p);
	}
}
