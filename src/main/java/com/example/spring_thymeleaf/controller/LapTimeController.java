package com.example.spring_thymeleaf.controller;

import com.example.spring_thymeleaf.dto.CreateLaptimeDTO;
import com.example.spring_thymeleaf.entities.LapTime;
import com.example.spring_thymeleaf.service.LapTimeService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/laptimes")
public class LapTimeController {

    private final LapTimeService lapTimeService;

    public LapTimeController(LapTimeService lapTimeService) {
        this.lapTimeService = lapTimeService;
    }

    @GetMapping
    public String findAll(Model model){
        List<LapTime> lapTimeList = lapTimeService.findLapTimes();
        model.addAttribute("lapTimeList", lapTimeList);
        return "laptime";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LapTime> findById(@PathVariable("id") int id){
        try {
            return ResponseEntity.ok(lapTimeService.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public String addLapTime(@ModelAttribute CreateLaptimeDTO createLaptimeDTO, HttpServletResponse httpServletResponse){
        LapTime lapTime = lapTimeService.addLapTime(createLaptimeDTO.lapTime());
        httpServletResponse.setHeader("id", String.valueOf(lapTime.getId()));
        return "redirect:/laptimes";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        try {
            lapTimeService.deleteById(id);
            return ResponseEntity.status(303).header("Location", "/laptimes").build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(404).header("Location", "/laptimes").build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
            lapTimeService.deleteAll();
            return ResponseEntity.status(204).header("Location", "/laptimes").build();
    }

}
