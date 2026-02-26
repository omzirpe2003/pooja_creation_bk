package com.example.repo.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Model.products.Products;

public interface ProductRepo extends JpaRepository<Products, Long>{
	
	List<Products> findByCategoryName(String categoryName);
	List<Products> findByCategoryId(Integer categoryId);

}