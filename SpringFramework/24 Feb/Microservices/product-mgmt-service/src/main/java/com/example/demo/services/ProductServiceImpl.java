package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepo repo;
	
	@Override
	public String addProduct(Product p) {
		repo.save(p);
		return "Successfully added new product !";
	}

	@Override
	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	@Override
	public Optional<Product> getProductById(int pid) {
		return repo.findById(pid);
	}

}
