package com.example.demo.entity;


import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private String destination;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double fare;

    @OneToMany(mappedBy = "route")
    private List<Bus> buses;

    public Route() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalTime departureTime) { this.departureTime = departureTime; }
    public LocalTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalTime arrivalTime) { this.arrivalTime = arrivalTime; }
    public double getFare() { return fare; }
    public void setFare(double fare) { this.fare = fare; }
    public List<Bus> getBuses() { return buses; }
    public void setBuses(List<Bus> buses) { this.buses = buses; }
}
