package com.product.dao;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.product.entities.Product;
import com.product.entities.User;

@RestController
public class SearchController {
	
	@Autowired
	private UserRepository userRepository;	
	@Autowired
	private ProductRepository productRepository;
	
	//search handeler
	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query") String query,Principal principal)
	{
		System.out.println(query);
		
		User user = this.userRepository.getUserByUserName(principal.getName());
		
		List<Product> products = this.productRepository.findByProductNameContainingAndUser(query, user);
		return ResponseEntity.ok(products);
	}
}
