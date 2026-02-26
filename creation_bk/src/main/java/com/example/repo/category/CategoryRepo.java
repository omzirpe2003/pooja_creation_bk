package com.example.repo.category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Model.category.CategoryTable;

public interface CategoryRepo extends JpaRepository<CategoryTable, Long>{
	
	
	Optional<CategoryTable> findByName(String name);
	
}