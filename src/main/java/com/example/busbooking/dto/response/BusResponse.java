package com.example.busbooking.dto.response;

import com.example.busbooking.enums.BusType;
import java.time.LocalTime;

public class BusResponse {
    
    private Long id;
    private String busNumber;
    private String source;
    private String destination;
    private BusType busType;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Double fare;
    private Integer availableSeats;
    private Integer duration;
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getBusNumber() {
        return busNumber;
    }
    
    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }
    
    public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public BusType getBusType() {
        return busType;
    }
    
    public void setBusType(BusType busType) {
        this.busType = busType;
    }
    
    public LocalTime getDepartureTime() {
        return departureTime;
    }
    
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
    
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    public Double getFare() {
        return fare;
    }
    
    public void setFare(Double fare) {
        this.fare = fare;
    }
    
    public Integer getAvailableSeats() {
        return availableSeats;
    }
    
    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
