package com.example.controller.Review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.Review.CreateReviewRequest;
import com.example.Model.Review.EditReviewRequest;
import com.example.Model.Review.ReviewResponse;
import com.example.Model.authmodel.UserPrincipal;
import com.example.Model.response.ApiResponse;
import com.example.Service.Review.ReviewService;
@CrossOrigin(value = "*")
@RestController
@RequestMapping("api/rivew")
public class ReviewController{
	@Autowired
	private ReviewService reviewService; 
	@PostMapping("/create")
	public ResponseEntity<ApiResponse<ReviewResponse>> createRivew(@RequestBody CreateReviewRequest response, @AuthenticationPrincipal UserPrincipal user){
		ReviewResponse response1=reviewService.createReview(response,user);
		ApiResponse<ReviewResponse> body=new ApiResponse<ReviewResponse>(true,"Add new Review",response1,null);
		
		return ResponseEntity.ok(body);
	}
	
	
	@GetMapping("/getRivew")
	public ResponseEntity<ApiResponse<List<ReviewResponse>>> getRivew(){
		List<ReviewResponse> response =reviewService.getAll();	
		ApiResponse<List<ReviewResponse>> body=new ApiResponse<List<ReviewResponse>>(true,"All Reviews",response,null);
		
		return ResponseEntity.ok(body);
	}
	
	
	@PutMapping("/edit-review")
	public ResponseEntity<ApiResponse<ReviewResponse>> createRivew(@RequestBody EditReviewRequest response, @AuthenticationPrincipal UserPrincipal user){
		ReviewResponse response1=reviewService.editReview(response,user.getId());
		ApiResponse<ReviewResponse> body=new ApiResponse<ReviewResponse>(true,"Edit Review",response1,null);
		
		return ResponseEntity.ok(body);
	}
	
}