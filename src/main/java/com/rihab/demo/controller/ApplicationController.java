package com.rihab.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rihab.demo.exception.ResourceNotFoundException;
import com.rihab.demo.model.Application;
import com.rihab.demo.repository.ApplicationRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping("/api")
@Api(value="Applications")
public class ApplicationController {
	
	@Autowired
	private ApplicationRepo applicationRepo;
	
	@ApiOperation(value="Shows the list of available applications for review",
			notes = "No parameters are required here",
			response=Application.class)
	@GetMapping("/applications")
	public List<Application> getApplications(){
		return applicationRepo.findAll();
	}
	
	//@RequestMapping("/applications")
	//@ResponseBody
	//public java.util.List<Application> getApplications()
	//{
		//return applicationRepo.findAll();
	//}
	
	
	//@RequestMapping("/applications/{applicationId}")
	//@ResponseBody
	
	@ApiOperation(value="Returns the application details based on the application ID",
			notes = "Please enter the application ID",
			response=Application.class)
	@GetMapping("/applications/{applicationId}")
	public List<Application> getApplication(@ApiParam(value="ID of the application you want to retrieve", required=true)  
	@PathVariable("applicationId") List<Long> applicationId)
	{
		return applicationRepo.findAllById(applicationId);
	}
	
	//check @validated
	@ApiOperation(value="Adds a new application",
			notes = "Please enter the details of the application you want to add")
	@PostMapping("/applications")
	public Application createApplication(@ApiParam(value="Details about the new application", required=true) @RequestBody Application application) {
		
		return applicationRepo.save(application);
	}
	
	//update section
	@ApiOperation(value="Updates an application based on the application ID",
			notes = "Please enter the application ID")
	@PutMapping("/applications/{applicationId}")
    public Application updateApplication(@ApiParam(value="ID of the application you want to update", required=true) 
    @PathVariable Long applicationId, @ApiParam(value="Changes you want to make to the application", required=true) @RequestBody Application applicationRequest) {
        return applicationRepo.findById(applicationId).map(application -> {
            application.setName(applicationRequest.getName());
            application.setDescription(applicationRequest.getDescription());
            application.setCategory(applicationRequest.getCategory());
            return applicationRepo.save(application);
        }).orElseThrow(() -> new ResourceNotFoundException("ApplicationId " + applicationId + " not found"));
    }
	
	//Delete section
	
	@ApiOperation(value="Removes an application",
			notes = "Please enter the application ID")
	@DeleteMapping("/applications/{applicationId}")
    public ResponseEntity<?> deleteApplication(@ApiParam(value="ID of the application you want to remove", required=true)
    @PathVariable Long applicationId) {
        return applicationRepo.findById(applicationId).map(application -> {
            applicationRepo.delete(application);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ApplicationId " + applicationId + " not found"));
   }
	
	
	
	

}
