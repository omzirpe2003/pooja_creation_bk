package com.example.Service.product;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ExeptionHandel.ProductNotFoundException;
import com.example.Model.category.CategoryRequest;
import com.example.Model.category.CategoryTable;
import com.example.Model.products.ProductRequestDto;
import com.example.Model.products.ProductResponseDto;
import com.example.Model.products.Products;
import com.example.Model.response.ApiResponse;
import com.example.Service.cloudinary.CloudinaryService;
import com.example.repo.category.CategoryRepo;
import com.example.repo.product.OrderItemRepo;
import com.example.repo.product.ProductRepo;

import jakarta.transaction.Transactional;

@Service
public class ProductServce {
	@Autowired
	private CloudinaryService cloudinaryService;
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private OrderItemRepo orderRepo;
	
	
	public ProductResponseDto addProduct(ProductRequestDto dto) {
		
		Products product=new Products();
		
		CategoryTable categoryTable =categoryRepo.findByName(dto.getCategoryName()).orElseThrow(()-> new RuntimeException("Category not found: "+dto.getCategoryName()));
		String mainImage=uploadIfPresent(dto.getMainImg());
		String image1=uploadIfPresent(dto.getImage1());
		String image2=uploadIfPresent(dto.getImage2());
		
		if (mainImage != null) {
		    product.setMainImg(mainImage);
		} else {
		    throw new RuntimeException("Upload Main Image");
		}

		if (image1 != null) {
		    product.setImage1(image1);
		}

		if (image2 != null) { // <--- Changed from 'else if' to 'if'
		    product.setImage2(image2);
		}
		
		product.setCategoryId(categoryTable.getId());
		product.setCategoryName(categoryTable.getName());
		product.setAvailable(true);
		product.setProductName(dto.getProductName());
		product.setPrice(dto.getPrice());
		product.setCreatedAt(LocalDateTime.now());
		product.setDisc(dto.getDisc());
		product.setSpecification(dto.getSpecification());
		
		
		Products response =productRepo.save(product);
		ProductResponseDto responseDto =new ProductResponseDto();

		System.out.println("In main url "+ response.getMainImg());

		System.out.println("In 1st url "+response.getImage1());

		System.out.println("In 2nd url "+ response.getImage2());
		responseDto.setId(response.getId());
		responseDto.setIsAvailable(response.isAvailable());
		responseDto.setCategoryId(response.getCategoryId());
		responseDto.setCreatedAt(response.getCreatedAt());
		responseDto.setMainImgUrl(response.getMainImg());
		responseDto.setPrice(response.getPrice());
		responseDto.setImage1Url(response.getImage1());
		responseDto.setImage2Url(response.getImage2());
		responseDto.setSpecification(response.getSpecification());
		responseDto.setCategoryName(response.getCategoryName());
		responseDto.setProductName(response.getProductName());
		responseDto.setDisc(response.getDisc());
		
		return responseDto;
		
		
		
	}
	
	private String uploadIfPresent(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        try {
            Map<?, ?> result = cloudinaryService.uploadImage(file);
            return (String) result.get("secure_url");
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload image: " + e.getMessage());
        }
    }

	public List<ProductResponseDto> getProducts() {
		 
		return productRepo.findAll().stream().map(ProductResponseDto::new).collect(Collectors.toList());
		    
	}

	public ProductResponseDto getProductById(Long id) {
		Products response=productRepo.findById(id).orElseThrow(()-> new RuntimeException("No Product is present in this id: "+id));
		return new ProductResponseDto(response);
	}

	public List<ProductResponseDto> getByCategoryName(String request) {

	    List<Products> products = productRepo.findByCategoryName(request);

	    if(products.isEmpty()) {
	        throw new ProductNotFoundException("Products not found");
	    }

	    return products.stream()
	            .map(ProductResponseDto::new)
	            .collect(Collectors.toList());
	}
	
	public ProductResponseDto updateProduct(Long id, String name, BigDecimal price, String disc, 
	                                       String catName, String spec, Boolean available,
	                                       MultipartFile mImg, MultipartFile img1, MultipartFile img2) {
	    
	    Products product = productRepo.findById(id)
	            .orElseThrow(() -> new ProductNotFoundException("Product not found"));

	    // Update text fields only if provided
	    if (name != null) product.setProductName(name);
	    if (price != null) product.setPrice(price);
	    if (disc != null) product.setDisc(disc);
	    if (spec != null) product.setSpecification(spec);
	    if (available != null) product.setAvailable(available);
	    
	    if (catName != null) {
	        CategoryTable cat = categoryRepo.findByName(catName).orElseThrow();
	        product.setCategoryId(cat.getId());
	        product.setCategoryName(cat.getName());
	    }

	    // Update images only if new files are uploaded
	    if (mImg != null && !mImg.isEmpty()) product.setMainImg(uploadIfPresent(mImg));
	    if (img1 != null && !img1.isEmpty()) product.setImage1(uploadIfPresent(img1));
	    if (img2 != null && !img2.isEmpty()) product.setImage2(uploadIfPresent(img2));

	    return new ProductResponseDto(productRepo.save(product));
	}
	
	
	public List<ProductResponseDto> getByCategoryId(Integer request) {

	    List<Products> products = productRepo.findByCategoryId(request);

	    if(products.isEmpty()) {
	        throw new RuntimeException("Category not found");
	    }

	    return products.stream()
	            .map(ProductResponseDto::new)
	            .collect(Collectors.toList());
	}

    public ProductResponseDto updateProductAvailability(Long id, boolean isAvailable) {
        // 1. Find the product
        Products product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // 2. Set the status
        product.setAvailable(isAvailable);

        // 3. Save
        Products updatedProduct = productRepo.save(product);

        // 4. Map to DTO (ensure your constructor maps disc and specification)
        return new ProductResponseDto(updatedProduct);
    }

    @Transactional
    public boolean deleteProductById(Long id) {

        if (!productRepo.existsById(id)) {
            return false;
        }

        // First delete child records
        orderRepo.deleteByProductId(id);

        // Then delete product
        productRepo.deleteById(id);

        return true;
    }

	
	
	
	
	
	
	
	
}