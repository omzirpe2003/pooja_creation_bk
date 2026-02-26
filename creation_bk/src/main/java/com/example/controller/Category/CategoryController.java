package com.example.controller.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.category.CategoryRequest;
import com.example.Model.category.CategoryTable;
import com.example.Model.response.ApiResponse;
import com.example.Service.Category.CategoryService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 🔹 ADD Category
    @PostMapping("/addCategory")
    public ResponseEntity<ApiResponse<CategoryTable>> addCategory(
            @RequestParam String type,
            @RequestParam String descrip) {

        CategoryTable saved = categoryService.addCetegory(type, descrip);

        ApiResponse<CategoryTable> body =
                new ApiResponse<>(true, "Category added successfully", saved, null);

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    // 🔹 GET ALL Categories
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<CategoryTable>>> getAllCategories() {

        List<CategoryTable> response = categoryService.getCategory();

        ApiResponse<List<CategoryTable>> body =
                new ApiResponse<>(true, "All Categories", response, null);

        return ResponseEntity.ok(body);
    }

    // 🔹 GET Category by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryTable>> getCategoryById(@PathVariable Long id) {

        CategoryTable response = categoryService.getCategoryById(id);

        ApiResponse<CategoryTable> body =
                new ApiResponse<>(true, "Category found", response, null);

        return ResponseEntity.ok(body);
    }

    // 🔹 GET Category by Name
    @GetMapping("/name")
    public ResponseEntity<ApiResponse<CategoryTable>> getCategoryByName(
            @RequestBody CategoryRequest name) {

        CategoryTable response = categoryService.getCategoryByName(name.category());

        ApiResponse<CategoryTable> body =
                new ApiResponse<>(true, "Category found", response, null);

        return ResponseEntity.ok(body);
    }
}