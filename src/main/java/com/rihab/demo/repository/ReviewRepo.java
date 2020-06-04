package com.rihab.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rihab.demo.model.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
	
	List<Review> findByApplicationId(Long applicationId);
	Optional<Review> findByIdAndApplicationId (Long id, Long applicationId);
	
	

}
