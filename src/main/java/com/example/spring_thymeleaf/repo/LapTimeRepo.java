package com.example.spring_thymeleaf.repo;

import com.example.spring_thymeleaf.entities.LapTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LapTimeRepo extends JpaRepository<LapTime, Integer> {

}
