package com.example.spring_thymeleaf.service;

import com.example.spring_thymeleaf.entities.LapTime;
import com.example.spring_thymeleaf.repo.LapTimeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LapTimeService {

    private final LapTimeRepo lapTimeRepo;

    public LapTimeService(LapTimeRepo lapTimeRepo) {
        this.lapTimeRepo = lapTimeRepo;
    }

    public List<LapTime> findLapTimes() {
        List<LapTime> lapTimes = lapTimeRepo.findAll();
        // TODO: Sort and limit to only 5 best times
        return  lapTimes;
    }

    public LapTime findById(int id) {
        return lapTimeRepo.findById(id).orElseThrow();
    }

    public LapTime addLapTime(String lapTime) {
        return lapTimeRepo.save(new LapTime(Double.parseDouble(lapTime)));
    }

    public void deleteById(int id) {
        lapTimeRepo.deleteById(id);
    }

    public void deleteAll() {
        lapTimeRepo.deleteAll();
    }
}
