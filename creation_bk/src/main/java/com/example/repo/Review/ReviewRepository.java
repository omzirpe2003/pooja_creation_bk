package com.example.repo.Review;
import com.example.Model.Review.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Optional<Review> findByIdAndUserId(Long reviewId, Long userId);
    boolean existsByProductIdAndUserId(Long productId, Long userId);

    List<Review> findByProductId(Long productId);
}
