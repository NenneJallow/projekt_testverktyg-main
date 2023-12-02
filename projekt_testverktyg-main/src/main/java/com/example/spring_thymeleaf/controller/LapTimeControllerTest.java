package com.example.spring_thymeleaf.controller;

import com.example.spring_thymeleaf.dto.CreateLaptimeDTO;
import com.example.spring_thymeleaf.entities.LapTime;
import com.example.spring_thymeleaf.repo.LapTimeRepo;
import com.example.spring_thymeleaf.service.LapTimeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(LapTimeController.class)
public class LapTimeControllerTest {

    // Create a mock for LapTimeRepo
    LapTimeRepo lapTimeRepoMock = Mockito.mock(LapTimeRepo.class);

    // Create an instance of LapTimeService with the mock
    LapTimeService lapTimeService = new LapTimeService(lapTimeRepoMock);

    @Autowired
    private MockMvc mockMvc;
  //  LapTimeController mockMvc = Mockito.mock(LapTimeController.class);

    @Test
    public void testFindAll() throws Exception {
        // Mock data
        LapTime lapTime = new LapTime();
        lapTime.setId(1);
        lapTime.setLapTime(1.5);

        List<LapTime> lapTimeList = Arrays.asList(lapTime);

        Mockito.when(lapTimeService.findLapTimes()).thenReturn(lapTimeList);

        // Perform GET request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/laptimes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("lapTimeList"));
    }

    @Test
    public void testFindById() throws Exception {
        // Mock data
        LapTime lapTime = new LapTime();
        lapTime.setId(1);
        lapTime.setLapTime(1.5);

        Mockito.when(lapTimeService.findById(1)).thenReturn(lapTime);

        // Perform GET request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/laptimes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\":1,\"lapTime\":1.5}"));
    }

    @Test
    public void testAddLapTime() throws Exception {
        // Mock data


        LapTime lapTime = new LapTime();
        lapTime.setId(1);
        lapTime.setLapTime(1.5);

        Mockito.when(lapTimeService.addLapTime("1.5")).thenReturn(lapTime);

        // Perform POST request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.post("/laptimes")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("lapTime", "1.5"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testDeleteById() throws Exception {
        // Mock data
        Mockito.doNothing().when(lapTimeService).deleteById(1);

        // Perform DELETE request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.delete("/laptimes/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testDeleteAll() throws Exception {
        // Mock data
        Mockito.doNothing().when(lapTimeService).deleteAll();

        // Perform DELETE request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.delete("/laptimes"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
