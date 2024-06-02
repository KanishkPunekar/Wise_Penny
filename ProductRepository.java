package com.product.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.entities.Product;
import com.product.entities.User;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("from Product as p where p.user.id =:userId")
	public Page<Product> findProductsByUser( @Param("userId") int userId,Pageable pageable);
	
	//search method
	public List<Product> findByProductNameContainingAndUser(String productName,User user);
}
