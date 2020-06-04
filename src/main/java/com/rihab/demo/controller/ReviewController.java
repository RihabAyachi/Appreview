package com.rihab.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.rihab.demo.exception.ResourceNotFoundException;
import com.rihab.demo.model.Application;
import com.rihab.demo.model.Review;
import com.rihab.demo.repository.ApplicationRepo;
import com.rihab.demo.repository.ReviewRepo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class ReviewController {

	
	@Autowired
	private ReviewRepo reviewRepo;
	
	@Autowired
	private ApplicationRepo applicationRepo;
	
	
//	@RequestMapping("/add")
//	public String addAlien(Alien alien)
//	{
//		repo.save(alien);
//		return "home.jsp";
//	}
	
	@ApiOperation(value="Shows the list of reviews of a given application",
			notes = "Please enter the application ID")
	@GetMapping("/applications/{applicationId}/reviews")
    public List<Review> getAllReviewsByApplicationId(@ApiParam(value="ID of the application", required=true)
    @PathVariable (value = "applicationId") Long applicationId) {
        return reviewRepo.findByApplicationId(applicationId);
    }

	@ApiOperation(value="Posts a review to a given application",
			notes = "Please type your review")
    @PostMapping("/applications/{applicationId}/reviews")
    public Review createReview(@ApiParam(value="ID of the application you want to review", required=true)
    @PathVariable (value = "applicationId") Long applicationId, @ApiParam(value="Write your review", required=true)
    @RequestBody Review review) {

    	Optional<Application> app = applicationRepo.findById(applicationId);
    	app.map(application -> {
    		review.setApplication(application);
            return reviewRepo.save(review);
    	});
    	return review;
       
    	
    	
//    	return applicationRepo.findById(applicationId).map(application -> {
//            review.setApplication(application);
//            return reviewRepo.save(review);
//        }).orElseThrow(() -> new ResourceNotFoundException("ApplicationId " + applicationId + " not found"));
    }
	
	
	
	
	
}
