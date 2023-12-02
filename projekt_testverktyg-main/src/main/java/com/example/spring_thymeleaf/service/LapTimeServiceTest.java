package com.example.spring_thymeleaf.service;

import com.example.spring_thymeleaf.entities.LapTime;
import com.example.spring_thymeleaf.repo.LapTimeRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LapTimeServiceTest {

    @Test
    public void testFindTop5LapTimes() {
        // Create a mock for LapTimeRepo
        LapTimeRepo lapTimeRepoMock = Mockito.mock(LapTimeRepo.class);

        // Create an instance of LapTimeService with the mock
        LapTimeService lapTimeService = new LapTimeService(lapTimeRepoMock);

        // Mock the behavior of findAll() to return a list of lap times
        List<LapTime> mockLapTimes = Arrays.asList(
                new LapTime(1.5),
                new LapTime(1.2),
                new LapTime(1.8),
                new LapTime(1.3),
                new LapTime(1.6),
                new LapTime(1.7)
        );
        when(lapTimeRepoMock.findAll()).thenReturn(mockLapTimes);

        // Call the method being tested
        List<LapTime> top5LapTimes = lapTimeService.findLapTimes();

        // Assert the expected results
        assertEquals(5, top5LapTimes.size());

    }
}
