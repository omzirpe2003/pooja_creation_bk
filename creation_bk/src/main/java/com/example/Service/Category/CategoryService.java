package com.example.Service.Category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ExeptionHandel.CategoryAlreadyExistsException;
import com.example.ExeptionHandel.CategoryNotFoundException;
import com.example.Model.category.CategoryTable;
import com.example.repo.category.CategoryRepo;

@Service
public class CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
		
	public CategoryTable addCetegory(String type, String descrip) {
		if(type==null || descrip==null) {
			throw new IllegalArgumentException("Category type cannot be null or empty");
		}if(categoryRepo.findByName(type).isPresent()) {
			throw new CategoryAlreadyExistsException(
                    "Category with name '" + type + "' already exists");
        }
		CategoryTable category = new CategoryTable();
        category.setName(type);
        category.setDescription(descrip);
        return categoryRepo.save(category);
	}
	
	public CategoryTable getCategoryById(Long id) {
	    return categoryRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
	}
	public CategoryTable getCategoryByName(String name) {
	    return categoryRepo.findByName(name).orElseThrow(() -> new CategoryNotFoundException("Category not found with name: " + name));
	}

	public List<CategoryTable> getCategory() {
	    return categoryRepo.findAll().stream().map(cat->{
	    	CategoryTable dto=new CategoryTable();
	    	dto.setDescription(cat.getDescription());
	    	dto.setId(cat.getId());
	    	dto.setName(cat.getName());
	    	return dto;
	    }).collect(Collectors.toList());
	}
	
	
}