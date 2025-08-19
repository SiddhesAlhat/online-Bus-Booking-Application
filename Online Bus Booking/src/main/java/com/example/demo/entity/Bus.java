package com.example.demo.entity;


import java.util.List;

import jakarta.persistence.*;

@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNumber;
    private String type; // AC / NON-AC
    private int capacity;

    @ManyToOne
    private Route route;

    @OneToMany(mappedBy = "bus")
    private List<Booking> bookings;

    public Bus() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public Route getRoute() { return route; }
    public void setRoute(Route route) { this.route = route; }
    public List<Booking> getBookings() { return bookings; }
    public void setBookings(List<Booking> bookings) { this.bookings = bookings; }
    
}
