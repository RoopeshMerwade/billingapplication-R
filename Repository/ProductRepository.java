package com.billingapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.billingapplication.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("SELECT p FROM Product p WHERE p.prodName LIKE %:search% OR p.prodDescription LIKE %:search%")
	public List<Product> searchProduct(@Param("search")String search);
}
