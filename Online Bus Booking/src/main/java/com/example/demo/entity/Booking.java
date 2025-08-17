package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @ManyToOne private User user;
  @ManyToOne private Bus bus;
  private Date travelDate;
  private String status; // e.g., "PAID"

public Booking(int id) {
	super();
	this.id = id;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Bus getBus() {
	return bus;
}
public void setBus(Bus bus) {
	this.bus = bus;
}
public Date getTravelDate() {
	return travelDate;
}
public void setTravelDate(Date date) {
	this.travelDate = date;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Booking() {
	super();
	this.id = id;
	this.user = user;
	this.bus = bus;
	this.travelDate = travelDate;
	this.status = status;
}
  
}

