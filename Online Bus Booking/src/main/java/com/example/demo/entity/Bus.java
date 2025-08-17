package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Bus {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String busNumber;
  private String timing;
  @ManyToOne
  @JoinColumn(name = "route_id")
  private Route route;
  private int seats = 40;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getBusNumber() {
	return busNumber;
}
public void setBusNumber(String busNumber) {
	this.busNumber = busNumber;
}
public String getTiming() {
	return timing;
}
public void setTiming(String timing) {
	this.timing = timing;
}
public Route getRoute() {
	return route;
}
public void setRoute(Route route) {
	this.route = route;
}
public int getSeats() {
	return seats;
}
public void setSeats(int seats) {
	this.seats = seats;
}
public Bus() {
	super();
	this.id = id;
	this.busNumber = busNumber;
	this.timing = timing;
	this.route = route;
	this.seats = seats;
}
  
}

