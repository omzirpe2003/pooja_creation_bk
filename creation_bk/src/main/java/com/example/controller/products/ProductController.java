
package com.example.controller.products;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartFile;

import com.example.Model.category.CategoryRequest;
import com.example.Model.products.ProductRequestDto;
import com.example.Model.products.ProductResponseDto;
import com.example.Model.response.ApiResponse;
import com.example.Service.product.ProductServce;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product/")
public class ProductController {

    @Autowired
    private ProductServce productServcei;

    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<ProductResponseDto>> addProduct(
            @RequestParam String productName,
            @RequestParam BigDecimal price,
            @RequestParam String disc,
            @RequestParam MultipartFile mainImg,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) MultipartFile image1,
            @RequestParam(required = false) MultipartFile image2,
            @RequestParam(required = false) Boolean isAvailable,
            @RequestParam(required = false) String specification) {
    	System.out.println("Product name: "+productName);
    	System.out.println("Category: "+categoryName);
        // validation
        if (productName == null || productName.isBlank()) {
            throw new RuntimeException("Product name is empty");
        }

        if (mainImg.isEmpty()) {
            throw new RuntimeException("Product main image is empty");
        }

        ProductRequestDto dto = new ProductRequestDto();
        dto.setCategoryName(categoryName);
        dto.setImage1(image1);
        dto.setImage2(image2);
        dto.setIsAvailable(isAvailable);
        dto.setMainImg(mainImg);
        dto.setPrice(price);
        dto.setProductName(productName);
        dto.setSpecification(specification);
        dto.setDisc(disc);

        ProductResponseDto response = productServcei.addProduct(dto);

        ApiResponse<ProductResponseDto> body = new ApiResponse<>(
                true, "Product added successfully", response, null);

        return ResponseEntity.ok(body);
    }

    @GetMapping("getProducts")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getAllProducts() {
        List<ProductResponseDto> response = productServcei.getProducts();
        return ResponseEntity.ok(new ApiResponse<>(true, "Get All Products", response, null));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProductById(@PathVariable Long id) {
        ProductResponseDto response = productServcei.getProductById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Get Product", response, null));
    }

    @GetMapping("hello")
    public String getHello() {
        return "Hello";
    }
    @GetMapping("getByName/{category}")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getByCategoryName(@PathVariable String category){
    	List<ProductResponseDto> response = productServcei.getByCategoryName(category);
    	ApiResponse<List<ProductResponseDto>> body=new ApiResponse<List<ProductResponseDto>>();
    	body.setData(response);
    	body.setErrorCode(null);
    	body.setMessage("This are the Products");
    	body.setSuccess(true);
    	
    	return ResponseEntity.ok(body);
    }
    
    @GetMapping("getById/{id}")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getByCategoryId(@PathVariable Integer id){
    	List<ProductResponseDto> response =productServcei.getByCategoryId(id);
    	ApiResponse<List<ProductResponseDto>> body=new ApiResponse<List<ProductResponseDto>>();
    	body.setData(response);
    	body.setErrorCode(null);
    	body.setMessage("This are the Products");
    	body.setSuccess(true);
    	
    	return ResponseEntity.ok(body);
    }
    
    @PatchMapping("/{id}/availability")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateAvailability(
            @PathVariable Long id,
            @RequestParam boolean isAvailable) {
    	System.out.println("Staus: "+isAvailable);
        ProductResponseDto response = productServcei.updateProductAvailability(id, isAvailable);

        // Dynamic message based on status
        String statusMessage = isAvailable 
            ? "Product availability updated successfully" 
            : "Product unavailability updated successfully";

        ApiResponse<ProductResponseDto> body = new ApiResponse<>(
                true, 
                statusMessage, 
                response, 
                null
        );

        return ResponseEntity.ok(body);
    }
   
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productServcei.deleteProductById(id);

        if (isDeleted) {
            // success, message, data, errorCode
            ApiResponse<Boolean> response = new ApiResponse<>(true, "Product deleted successfully", true, null);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Boolean> response = new ApiResponse<>(false, "Product not found with ID: " + id, false, "NOT_FOUND_404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    @PatchMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(
            @PathVariable Long id,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) String disc,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String specification,
            @RequestParam(required = false) Boolean isAvailable,
            @RequestParam(required = false) MultipartFile mainImg,
            @RequestParam(required = false) MultipartFile image1,
            @RequestParam(required = false) MultipartFile image2) {

        ProductResponseDto response = productServcei.updateProduct(id, productName, price, disc, 
                                        categoryName, specification, isAvailable, 
                                        mainImg, image1, image2);

        return ResponseEntity.ok(new ApiResponse<>(true, "Product updated successfully", response, null));
    }
   
    
}
