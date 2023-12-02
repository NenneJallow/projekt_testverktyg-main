package com.example.spring_thymeleaf.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LapTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = false)
    private double LapTime;

    @Column()
    private Date date;

    public LapTime(double title) {
        this.LapTime = title;
        this.date = new Date();
    }

    public LapTime() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLapTime() {
        return LapTime;
    }

    public void setLapTime(double lapTime) {
        this.LapTime = lapTime;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}

