package com.rihab.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rihab.demo.model.Application;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long> {

}
