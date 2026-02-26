package com.example.Service.Review;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ExeptionHandel.ProductNotFoundException;
import com.example.ExeptionHandel.UserAlreadyAddedReviewException;
import com.example.Model.Review.CreateReviewRequest;
import com.example.Model.Review.EditReviewRequest;
import com.example.Model.Review.Review;
import com.example.Model.Review.ReviewResponse;
import com.example.Model.authmodel.UserModel;
import com.example.Model.authmodel.UserPrincipal;
import com.example.Model.products.Products;
import com.example.repo.Review.ReviewRepository;
import com.example.repo.product.ProductRepo;
import com.example.repo.user.UserRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	  @Autowired
	    private ProductRepo productRepository;

	    @Autowired
	    private UserRepository userRepository;
	public ReviewResponse createReview(CreateReviewRequest request,UserPrincipal user) {
		if (request.getRating() < 1 || request.getRating() > 5) {
            throw new IllegalArgumentException("Stars must be between 1 and 5");
        }
		
		
		if (reviewRepository.existsByProductIdAndUserId(
                request.getProductId(), user.getId())) {
            throw new UserAlreadyAddedReviewException("User already reviewed this product");
        }
		
		Products product=productRepository.findById(request.getProductId()).orElseThrow(()-> new ProductNotFoundException("Product not found"));
		
		UserModel userr = userRepository.findById(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		Review review = new Review();
        review.setStars(request.getRating());
        review.setMessage(request.getMessage());
        review.setUser(userr);
        review.setUserName(userr.getUsername());
        product.addReview(review);
        
        reviewRepository.save(review);
        
        return mapToResponse(review);
	}
	
	private ReviewResponse mapToResponse(Review review) {
		
        ReviewResponse res = new ReviewResponse();
        res.setProductName(review.getProduct().getProductName());
        res.setCategory(review.getProduct().getCategoryName());
        res.setReviewId(review.getId());
        res.setProductId(review.getProduct().getId());
        res.setUserName(review.getUser().getFullName());
        res.setStars(review.getStars());
        res.setMessage(review.getMessage());
        res.setCreatedAt(review.getCreatedAt());
        return res;
    }

	public List<ReviewResponse> getAll() {
		return reviewRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}
	
	
	public ReviewResponse editReview(EditReviewRequest request, Long loggedInUserId) {
		Review review=reviewRepository.findByIdAndUserId(request.getReviewId(), loggedInUserId).orElseThrow(()-> new RuntimeException("You are not allowed to edit this review"));
		if (request.getRating() < 1 || request.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
		review.setMessage(request.getMessage());
		review.setStars(request.getRating());
		reviewRepository.save(review);
		
		return mapToResponse(review);
		
	
	}
	
	
}